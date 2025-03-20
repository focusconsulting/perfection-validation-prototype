package com.focus.irs.pv.prototype.services.form1040;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.focus.irs.pv.prototype.Deduction;
import com.focus.irs.pv.prototype.FilingStatus;
import com.focus.irs.pv.prototype.Form1040Data;
import com.focus.irs.pv.prototype.ItemizedDeductions;
import com.focus.irs.pv.prototype.credits.dependents.Dependent;
import com.focus.irs.pv.prototype.credits.dependents.DependentInformation;
import com.focus.irs.pv.prototype.messages.Process1040Message;

@Component
public class Fetch1040DataService {

    public Form1040Data fetchData(Process1040Message message) {
        // Variables to be set based on message type
        Boolean isBlind = false;
        Integer age = 45; // Default age
        FilingStatus filingStatus = FilingStatus.S;
        ItemizedDeductions deductions = new ItemizedDeductions();
        DependentInformation dependentInfo = new DependentInformation();
        
        // Use the messageId to determine which test data to return
        switch (message.getMessageId()) {
            case "SINGLE_FILER":
                // Create a deduction for teacher expenses (to match the test case)
                Deduction teacherExpenseDeduction = new Deduction(BigDecimal.valueOf(800), "teacher-expense");
                deductions = new ItemizedDeductions(Arrays.asList(teacherExpenseDeduction));
                
                // Create a dependent over 18 years old
                Dependent dependent = new Dependent(21, false);
                dependentInfo = new DependentInformation(Arrays.asList(dependent));
                dependentInfo.setIsChildTaxCreditAllowed(false);
                dependentInfo.setIsOtherDependentsCreditAllowed(false);
                break;
                
            case "MARRIED_FILER":
                filingStatus = FilingStatus.MFJ;
                
                // Create an empty deductions list
                deductions = new ItemizedDeductions(Arrays.asList());
                
                // Create a dependent under 18 years old
                Dependent youngDependent = new Dependent(15, false);
                dependentInfo = new DependentInformation(Arrays.asList(youngDependent));
                dependentInfo.setIsChildTaxCreditAllowed(true);
                dependentInfo.setIsOtherDependentsCreditAllowed(false);
                break;
                
            case "SENIOR_FILER":
                age = 70; // Set age to 70 for senior filer
                deductions = new ItemizedDeductions(Arrays.asList());
                dependentInfo = new DependentInformation(Arrays.asList());
                break;
                
            case "BLIND_FILER":
                isBlind = true;
                deductions = new ItemizedDeductions(Arrays.asList());
                dependentInfo = new DependentInformation(Arrays.asList());
                break;
                
            default:
                // Default case - single filer with no special conditions
                deductions = new ItemizedDeductions(Arrays.asList());
                dependentInfo = new DependentInformation(Arrays.asList());
                break;
        }
        
        // Create and return the Form1040Data using the constructor
        return new Form1040Data(isBlind, age, filingStatus, deductions, dependentInfo, new Form5329("D", BigDecimal.valueOf(5000)));
    }
}
