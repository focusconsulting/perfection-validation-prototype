<!-- TODO: how to use LLM to generate business process and decisions -->

<!-- AI! Write an explanation of how an LLM can be leveraged to accelerate business process and decision authoring.  I used AnythingLLM and embedded the Form 943 instructions. The prompt I used was: 
"You are a tax expert and I am a Java developer.  Together we need to implement the rules, corrections and validations for the Form 943.  The technologies that I'm using is Apache Kie, specifically BPMN, DMN and DRL.  Please provide to me a description of the process that I will implement in BPMN as well as the decisions.

Important: you can assume that the input data has basic integrity. For example, our process does not need to confirm if something is correctly formatted date." -->

> # Revised Form 943 Implementation Guide for Apache Kie (Fully Automated)
> 
> As a tax expert, I'll outline a fully automated implementation for processing Form 943 (Employer's Annual Federal Tax Return for Agricultural Employees) using Apache Kie technologies, with a focus on DMN and automated processing of XML input data.

> ## BPMN Process Flow (Automated)
> 
> ### Main Process: Form943AutomatedProcessing

> 1. **Start Event**: Receive XML form data
> 2. **Service Task**: Parse XML and map to data model
> 3. **Service Task**: Validate XML structure and required fields
> 4. **Business Rule Task**: Validate filing eligibility (connects to FilingEligibilityDecision DMN)
> 5. **Gateway**: Check if eligible to file
>    - If not eligible: Redirect to error handling subprocess
>    - If eligible: Continue
> 6. **Business Rule Task**: Calculate tax amounts (connects to TaxCalculationDecision DMN)
> 7. **Business Rule Task**: Apply adjustments (connects to AdjustmentsDecision DMN)
> 8. **Business Rule Task**: Apply credits (connects to CreditsDecision DMN)
> 9. **Gateway**: Verify tax calculations
>    - If errors: Redirect to error handling subprocess
>    - If valid: Continue
> 10. **Business Rule Task**: Calculate final tax amount (connects to FinalTaxCalculationDecision DMN)
> 11. **Business Rule Task**: Validate filing deadline (connects to DeadlineAndPenaltyDecision DMN)
> 12. **Service Task**: Generate confirmation or error report
> 13. **Service Task**: Store processed form data
> 14. **End Event**: Processing completed

> ### Subprocess: ErrorHandlingProcess
> 1. **Start Event**: Error detected
> 2. **Service Task**: Log error details
> 3. **Service Task**: Generate error report
> 4. **End Event**: Return error response

> ## DMN Decision Models
> 
> ### 1. FilingEligibilityDecision
> **Inputs**:
> - hasAgriculturalEmployees (boolean)
> - paidWagesOver150 (boolean)
> - employedContinuousLaborer (boolean)
> 
> **Decision Table**:
> | Has Agricultural Employees | Paid Wages Over $150 | Employed Continuous Laborer | Is Eligible To File | Reason If Not Eligible |
> |---------------------------|----------------------|----------------------------|---------------------|------------------------|
> | true                      | true                 | -                          | true                | -                      |
> | true                      | false                | true                       | true                | -                      |
> | true                      | false                | false                      | false               | "Insufficient agricultural wages" |
> | false                     | -                    | -                          | false               | "No agricultural employees" |
> 
> **Outputs**:
> - isEligibleToFile (boolean)
> - reasonIfNotEligible (string)

> ### 2. TaxCalculationDecision
> **Inputs**:
> - totalWages
> - federalIncomeTaxWithheld
> - employerSocialSecurityTax
> - employeeSocialSecurityTax
> - medicareTax
> - additionalMedicareTax
> 
> **BKM Functions**:
> - calculateSocialSecurityTax(wages) = wages * 0.124
> - calculateMedicareTax(wages) = wages * 0.029
> - validateTaxCalculation(inputs) = [validation logic]
> 
> **Decision Logic**:
> - totalTaxes = federalIncomeTaxWithheld + employerSocialSecurityTax + employeeSocialSecurityTax + medicareTax + additionalMedicareTax
> - validationErrors = validateTaxCalculation(inputs)
> 
> **Outputs**:
> - calculatedTaxes (number)
> - validationErrors (list)
> - isValid (boolean)

> ### 3. AdjustmentsDecision
> **Inputs**:
> - calculatedTaxes (from TaxCalculationDecision)
> - fractionOfCentAdjustment
> - sickPayAdjustment
> - otherAdjustments
> 
> **Decision Logic**:
> - totalAdjustments = fractionOfCentAdjustment + sickPayAdjustment + otherAdjustments
> - adjustedTaxAmount = calculatedTaxes + totalAdjustments
> 
> **Outputs**:
> - totalAdjustments (number)
> - adjustedTaxAmount (number)

> ### 4. CreditsDecision
> **Inputs**:
> - adjustedTaxAmount (from AdjustmentsDecision)
> - hasQualifiedSmallBusinessResearchCredit (boolean)
> - creditAmount (number)
> - form8974Attached (boolean)
> 
> **Decision Table**:
> | Has Research Credit | Credit Amount | Form 8974 Attached | Eligible Credit Amount | Requires Form 8974 | Error Message |
> |--------------------|---------------|-------------------|------------------------|-------------------|---------------|
> | true               | > 0           | true              | creditAmount           | true              | -             |
> | true               | > 0           | false             | 0                      | true              | "Form 8974 required" |
> | true               | 0             | -                 | 0                      | false             | -             |
> | false              | -             | -                 | 0                      | false             | -             |
> 
> **Decision Logic**:
> - taxesAfterCredits = MAX(0, adjustedTaxAmount - eligibleCreditAmount)
> - isValid = (errorMessage == null || errorMessage == "")
> 
> **Outputs**:
> - eligibleCreditAmount (number)
> - requiresForm8974 (boolean)
> - errorMessage (string)
> - taxesAfterCredits (number)
> - isValid (boolean)

> ### 5. DeadlineAndPenaltyDecision
> **Inputs**:
> - filingDate (date)
> - depositsComplete (boolean)
> - depositDates (list of dates)
> 
> **Decision Table**:
> | Filing Date | Deposits Complete | Is Late | Can Use Extended Deadline | Reason |
> |-------------|-------------------|---------|---------------------------|--------|
> | ≤ 2025-01-31 | -                 | false   | -                         | -      |
> | > 2025-01-31, ≤ 2025-02-10 | true    | false   | true                      | -      |
> | > 2025-01-31, ≤ 2025-02-10 | false   | true    | false                     | "Deposits not made on time" |
> | > 2025-02-10 | -                 | true    | false                     | "Filed after deadline" |
> 
> **BKM Functions**:
> - calculatePenalty(daysLate, taxAmount) = [penalty calculation logic]
> - calculateInterest(daysLate, taxAmount) = [interest calculation logic]
> 
> **Outputs**:
> - isLate (boolean)
> - canUseExtendedDeadline (boolean)
> - reason (string)
> - penaltyAmount (number, calculated if late)
> - interestAmount (number, calculated if late)

> ### 6. FinalTaxCalculationDecision
> **Inputs**:
> - taxesAfterCredits (from CreditsDecision)
> - totalDeposits (number)
> - penaltyAmount (from DeadlineAndPenaltyDecision)
> - interestAmount (from DeadlineAndPenaltyDecision)
> 
> **Decision Logic**:
> - amountDue = taxesAfterCredits - totalDeposits + penaltyAmount + interestAmount
> - overpayment = IF amountDue < 0 THEN ABS(amountDue) ELSE 0
> - finalAmountDue = IF amountDue > 0 THEN amountDue ELSE 0
> 
> **Outputs**:
> - finalAmountDue (number)
> - overpayment (number)
> - balanceStatus (string: "Due", "Overpaid", or "Balanced")

> ## XML Data Structure
> 
> The system will expect an XML document with a structure similar to this:
> 
> ```xml
> <Form943>
>   <TaxYear>2024</TaxYear>
>   <FilingDate>2025-01-15</FilingDate>
>   <EmployerInfo>
>     <Name>Agricultural Enterprises Inc</Name>
>     <EIN>12-3456789</EIN>
>     <Address>...</Address>
>   </EmployerInfo>
>   <EmploymentData>
>     <HasAgriculturalEmployees>true</HasAgriculturalEmployees>
>     <PaidWagesOver150>true</PaidWagesOver150>
>     <EmployedContinuousLaborer>true</EmployedContinuousLaborer>
>   </EmploymentData>
>   <TaxData>
>     <TotalWages>250000.00</TotalWages>
>     <FederalIncomeTaxWithheld>35000.00</FederalIncomeTaxWithheld>
>     <EmployerSocialSecurityTax>15500.00</EmployerSocialSecurityTax>
>     <EmployeeSocialSecurityTax>15500.00</EmployeeSocialSecurityTax>
>     <MedicareTax>7250.00</MedicareTax>
>     <AdditionalMedicareTax>500.00</AdditionalMedicareTax>
>   </TaxData>
>   <Adjustments>
>     <FractionOfCentAdjustment>0.25</FractionOfCentAdjustment>
>     <SickPayAdjustment>-150.00</SickPayAdjustment>
>     <OtherAdjustments>0.00</OtherAdjustments>
>   </Adjustments>
>   <Credits>
>     <HasResearchCredit>true</HasResearchCredit>
>     <CreditAmount>2500.00</CreditAmount>
>     <Form8974Attached>true</Form8974Attached>
>   </Credits>
>   <Deposits>
>     <TotalDeposits>71100.25</TotalDeposits>
>     <DepositsComplete>true</DepositsComplete>
>     <DepositDates>
>       <Date>2024-04-15</Date>
>       <Date>2024-07-15</Date>
>       <Date>2024-10-15</Date>
>       <Date>2025-01-15</Date>
>     </DepositDates>
>   </Deposits>
> </Form943>
> ```

> ## Implementation Notes
> 
> 1. **XML Parsing**: Implement a service task to parse the incoming XML and map it to the data model used by the DMN decisions.
> 
> 2. **Validation Framework**: Create a comprehensive validation framework that checks:
>    - Required fields presence
>    - Data type validations
>    - Cross-field validations (e.g., if credits claimed, Form 8974 must be attached)
> 
> 3. **Decision Requirements Diagram (DRD)**: Create a comprehensive DRD showing dependencies between decisions:
>    - TaxCalculationDecision feeds into AdjustmentsDecision
>    - AdjustmentsDecision feeds into CreditsDecision
>    - CreditsDecision and DeadlineAndPenaltyDecision feed into FinalTaxCalculationDecision
> 
> 4. **Error Handling**: Implement robust error handling with detailed error messages:
>    - Validation errors (missing fields, invalid data)
>    - Business rule violations (eligibility issues, calculation errors)
>    - Processing errors (system issues)
> 
> 5. **Response Generation**: Create a service task that generates a structured response containing:
>    - Processing status (success/error)
>    - Calculation results
>    - Validation messages
>    - Any penalties or interest applied
> 
> 6. **Logging and Auditing**: Implement comprehensive logging of all processing steps for audit purposes.
> 
> This fully automated approach eliminates user tasks and focuses on processing XML input data through a series of DMN-based business rules and decisions. The system validates, calculates, and processes Form 943 data without manual intervention, providing a structured response that indicates the results of the processing.
