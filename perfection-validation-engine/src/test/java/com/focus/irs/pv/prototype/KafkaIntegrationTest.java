package com.focus.irs.pv.prototype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.testcontainers.springboot.KafkaSpringBootTestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.focus.irs.pv.prototype.messages.Process1040Message;

import io.cloudevents.core.builder.CloudEventBuilder;
@SpringBootTest(classes = PerfectionAndValidationPrototype.class)
@ContextConfiguration(initializers = KafkaSpringBootTestResource.Conditional.class)
@EmbeddedKafka(partitions = 1, topics = {KafkaIntegrationTest.TOPIC_PRODUCER, KafkaIntegrationTest.TOPIC_PROCESSED_CONSUMER})
public class KafkaIntegrationTest {

    // The starting point is an a Kafka message, but in order to unit test we're manually setting the message
    // and then starting from the next node
    private static final String FETCH_DATA_STARTING_NODE = "_A66E8BD2-B59A-41F1-9432-36B2D2CF4C20";

    public static final String TOPIC_PRODUCER = "form1040";
    public static final String TOPIC_PROCESSED_CONSUMER = "tams";
    public static final String TOPIC_CANCEL_CONSUMER = "ers";

    @Autowired
    @Qualifier("form1040processor")
    Process<? extends Model> form1040Process;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    private CountDownLatch latch = new CountDownLatch(1);
    private String receivedCloudEventId;
    private String receivedMessageData;

    @KafkaListener(topics = TOPIC_PROCESSED_CONSUMER, groupId = "test-group")
    public void listen(String message) {
        try {
            // Extract the cloud event ID from the message
            int idStartIndex = message.indexOf("\"id\":\"") + 6;
            int idEndIndex = message.indexOf("\"", idStartIndex);
            receivedCloudEventId = message.substring(idStartIndex, idEndIndex);
            
            // Extract the data portion of the cloud event
            int dataStartIndex = message.indexOf("\"data\":") + 7;
            int dataEndIndex = message.lastIndexOf("}");
            if (message.charAt(dataStartIndex) == '"') {
                // Handle base64 encoded data
                dataStartIndex++;
                dataEndIndex = message.lastIndexOf("\"", dataEndIndex);
            }
            receivedMessageData = message.substring(dataStartIndex, dataEndIndex);
            
            latch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testKafkaMessageProcessing() throws Exception {
        // Create a unique ID for tracking the cloud event
        String cloudEventId = UUID.randomUUID().toString();
        
        // Create the message
        Process1040Message message = new Process1040Message("SINGLE_FILER", "objectstore:456");
        String messageJson = objectMapper.writeValueAsString(message);
        
        // Create a cloud event
        CloudEventBuilder cloudEventBuilder = CloudEventBuilder.v1()
                .withId(cloudEventId)
                .withType("form1040")
                .withSource(java.net.URI.create("test"))
                .withData(messageJson.getBytes());
        
        // Send the message to the form1040 topic
        String cloudEventJson = objectMapper.writeValueAsString(cloudEventBuilder.build());
        kafkaTemplate.send(TOPIC_PRODUCER, cloudEventJson);
        
        // Wait for the response on the tams topic
        boolean messageReceived = latch.await(30, TimeUnit.SECONDS);
        assertTrue(messageReceived, "Message should be processed and received on tams topic");
        
        // Deserialize the data portion of the message into a Form1040ProcessingOutput
        Form1040ProcessingOutput output = objectMapper.readValue(receivedMessageData, Form1040ProcessingOutput.class);
        
        // Assert that the input is not null
        assertNotNull(output.getInput(), "The Form1040Data input should not be null");
    }
}
