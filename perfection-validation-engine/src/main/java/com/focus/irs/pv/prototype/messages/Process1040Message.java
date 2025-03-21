package com.focus.irs.pv.prototype.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Process1040Message {
    private String messageId;
    private String dataBlob;

    // Default constructor for Jackson
    public Process1040Message() {
    }

    @JsonCreator
    public Process1040Message(
            @JsonProperty("messageId") String messageId,
            @JsonProperty("dataBlob") String dataBlob) {
        this.messageId = messageId;
        this.dataBlob = dataBlob;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDataBlob() {
        return dataBlob;
    }

    public void setDataBlob(String dataBlob) {
        this.dataBlob = dataBlob;
    }
}
