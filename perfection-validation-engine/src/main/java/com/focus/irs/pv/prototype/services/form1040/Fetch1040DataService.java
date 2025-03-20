package com.focus.irs.pv.prototype.services.form1040;

import java.util.Arrays;

import com.focus.irs.pv.prototype.FilingStatus;
import com.focus.irs.pv.prototype.Form1040Data;
import com.focus.irs.pv.prototype.credits.dependents.DependentInformation;
import com.focus.irs.pv.prototype.messages.Process1040Message;

public class Fetch1040DataService {

    public Form1040Data fetchData(Process1040Message message) {
        // Create a simple Form1040Data object based on the messageId
        Form1040Data data = new Form1040Data();
        
        // Use the messageId to determine which test data to return
        switch (message.getMessageId()) {
            case "SINGLE_FILER":
                data.setFilingStatus(FilingStatus.S);
                data.setIsBlind(false);
                data.setIsOver65(false);
                break;
                
            case "MARRIED_FILER":
                data.setFilingStatus(FilingStatus.MFJ);
                data.setIsBlind(false);
                data.setIsOver65(false);
                break;
                
            case "SENIOR_FILER":
                data.setFilingStatus(FilingStatus.S);
                data.setIsBlind(false);
                data.setIsOver65(true);
                break;
                
            case "BLIND_FILER":
                data.setFilingStatus(FilingStatus.S);
                data.setIsBlind(true);
                data.setIsOver65(false);
                break;
                
            default:
                // Default case - single filer with no special conditions
                data.setFilingStatus(FilingStatus.S);
                data.setIsBlind(false);
                data.setIsOver65(false);
                break;
        }
        
        // Create empty dependent information
        DependentInformation dependentInfo = new DependentInformation();
        dependentInfo.setIsChildTaxCreditAllowed(false);
        dependentInfo.setIsOtherDependentsCreditAllowed(false);
        data.setDependentInformation(dependentInfo);
        
        return data;
    }
}
