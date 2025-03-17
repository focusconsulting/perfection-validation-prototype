package com.focus.irs.pv.prototype;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Form943Data {
    private Integer taxYear;
    private LocalDate filingDate;
    private EmployerInfo employerInfo;
    private EmploymentData employmentData;
    private TaxData taxData;
    private Adjustments adjustments;
    private Credits credits;
    private Deposits deposits;

    // Default constructor for Jackson
    public Form943Data() {
    }

    @JsonCreator
    public Form943Data(
            @JsonProperty("taxYear") Integer taxYear,
            @JsonProperty("filingDate") LocalDate filingDate,
            @JsonProperty("employerInfo") EmployerInfo employerInfo,
            @JsonProperty("employmentData") EmploymentData employmentData,
            @JsonProperty("taxData") TaxData taxData,
            @JsonProperty("adjustments") Adjustments adjustments,
            @JsonProperty("credits") Credits credits,
            @JsonProperty("deposits") Deposits deposits) {
        this.taxYear = taxYear;
        this.filingDate = filingDate;
        this.employerInfo = employerInfo;
        this.employmentData = employmentData;
        this.taxData = taxData;
        this.adjustments = adjustments;
        this.credits = credits;
        this.deposits = deposits;
    }

    public Integer getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(Integer taxYear) {
        this.taxYear = taxYear;
    }

    public LocalDate getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(LocalDate filingDate) {
        this.filingDate = filingDate;
    }

    public EmployerInfo getEmployerInfo() {
        return employerInfo;
    }

    public void setEmployerInfo(EmployerInfo employerInfo) {
        this.employerInfo = employerInfo;
    }

    public EmploymentData getEmploymentData() {
        return employmentData;
    }

    public void setEmploymentData(EmploymentData employmentData) {
        this.employmentData = employmentData;
    }

    public TaxData getTaxData() {
        return taxData;
    }

    public void setTaxData(TaxData taxData) {
        this.taxData = taxData;
    }

    public Adjustments getAdjustments() {
        return adjustments;
    }

    public void setAdjustments(Adjustments adjustments) {
        this.adjustments = adjustments;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public Deposits getDeposits() {
        return deposits;
    }

    public void setDeposits(Deposits deposits) {
        this.deposits = deposits;
    }

    // Nested classes for each section of the form
    public static class EmployerInfo {
        private String name;
        private String ein;
        private String address;

        // Default constructor for Jackson
        public EmployerInfo() {
        }

        @JsonCreator
        public EmployerInfo(
                @JsonProperty("name") String name,
                @JsonProperty("ein") String ein,
                @JsonProperty("address") String address) {
            this.name = name;
            this.ein = ein;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEin() {
            return ein;
        }

        public void setEin(String ein) {
            this.ein = ein;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class EmploymentData {
        private Boolean hasAgriculturalEmployees;
        private Boolean paidWagesOver150;
        private Boolean employedContinuousLaborer;

        // Default constructor for Jackson
        public EmploymentData() {
        }

        @JsonCreator
        public EmploymentData(
                @JsonProperty("hasAgriculturalEmployees") Boolean hasAgriculturalEmployees,
                @JsonProperty("paidWagesOver150") Boolean paidWagesOver150,
                @JsonProperty("employedContinuousLaborer") Boolean employedContinuousLaborer) {
            this.hasAgriculturalEmployees = hasAgriculturalEmployees;
            this.paidWagesOver150 = paidWagesOver150;
            this.employedContinuousLaborer = employedContinuousLaborer;
        }

        public Boolean getHasAgriculturalEmployees() {
            return hasAgriculturalEmployees;
        }

        public void setHasAgriculturalEmployees(Boolean hasAgriculturalEmployees) {
            this.hasAgriculturalEmployees = hasAgriculturalEmployees;
        }

        public Boolean getPaidWagesOver150() {
            return paidWagesOver150;
        }

        public void setPaidWagesOver150(Boolean paidWagesOver150) {
            this.paidWagesOver150 = paidWagesOver150;
        }

        public Boolean getEmployedContinuousLaborer() {
            return employedContinuousLaborer;
        }

        public void setEmployedContinuousLaborer(Boolean employedContinuousLaborer) {
            this.employedContinuousLaborer = employedContinuousLaborer;
        }
    }

    public static class TaxData {
        private BigDecimal reportedTax;
        private BigDecimal totalWages;
        private BigDecimal taxableWagesForSocialSecurity;
        private BigDecimal taxableWagesForMedicare;
        private BigDecimal taxableWagesForAdditionalMedicare;
        private BigDecimal federalIncomeTaxWithheld;
        private BigDecimal employerSocialSecurityTax;
        private BigDecimal employeeSocialSecurityTax;
        private BigDecimal medicareTax;
        private BigDecimal additionalMedicareTax;

        // Default constructor for Jackson
        public TaxData() {
        }

        @JsonCreator
        public TaxData(
                @JsonProperty("reportedTax") BigDecimal reportedTax,
                @JsonProperty("totalWages") BigDecimal totalWages,
                @JsonProperty("taxableWagesForSocialSecurity") BigDecimal taxableWagesForSocialSecurity,
                @JsonProperty("taxableWagesForMedicare") BigDecimal taxableWagesForMedicare,
                @JsonProperty("taxableWagesForAdditionalMedicare") BigDecimal taxableWagesForAdditionalMedicare,
                @JsonProperty("federalIncomeTaxWithheld") BigDecimal federalIncomeTaxWithheld,
                @JsonProperty("employerSocialSecurityTax") BigDecimal employerSocialSecurityTax,
                @JsonProperty("employeeSocialSecurityTax") BigDecimal employeeSocialSecurityTax,
                @JsonProperty("medicareTax") BigDecimal medicareTax,
                @JsonProperty("additionalMedicareTax") BigDecimal additionalMedicareTax) {
            this.reportedTax = reportedTax;
            this.totalWages = totalWages;
            this.taxableWagesForSocialSecurity = taxableWagesForSocialSecurity;
            this.taxableWagesForMedicare = taxableWagesForMedicare;
            this.taxableWagesForAdditionalMedicare = taxableWagesForAdditionalMedicare;
            this.federalIncomeTaxWithheld = federalIncomeTaxWithheld;
            this.employerSocialSecurityTax = employerSocialSecurityTax;
            this.employeeSocialSecurityTax = employeeSocialSecurityTax;
            this.medicareTax = medicareTax;
            this.additionalMedicareTax = additionalMedicareTax;
        }


        public BigDecimal getReportedTax() {
            return reportedTax;
        }

        public void setReportedTax(BigDecimal reportedTax) {
            this.reportedTax = reportedTax;
        }

        public BigDecimal getTotalWages() {
            return totalWages;
        }

        public void setTotalWages(BigDecimal totalWages) {
            this.totalWages = totalWages;
        }

        public BigDecimal getTaxableWagesForSocialSecurity() {
            return taxableWagesForSocialSecurity;
        }

        public void setTaxableWagesForSocialSecurity(BigDecimal taxableWagesForSocialSecurity) {
            this.taxableWagesForSocialSecurity = taxableWagesForSocialSecurity;
        }

        public BigDecimal getTaxableWagesForMedicare() {
            return taxableWagesForMedicare;
        }

        public void setTaxableWagesForMedicare(BigDecimal taxableWagesForMedicare) {
            this.taxableWagesForMedicare = taxableWagesForMedicare;
        }

        public BigDecimal getTaxableWagesForAdditionalMedicare() {
            return taxableWagesForAdditionalMedicare;
        }

        public void setTaxableWagesForAdditionalMedicare(BigDecimal taxableWagesForAdditionalMedicare) {
            this.taxableWagesForAdditionalMedicare = taxableWagesForAdditionalMedicare;
        }

        public BigDecimal getFederalIncomeTaxWithheld() {
            return federalIncomeTaxWithheld;
        }

        public void setFederalIncomeTaxWithheld(BigDecimal federalIncomeTaxWithheld) {
            this.federalIncomeTaxWithheld = federalIncomeTaxWithheld;
        }

        public BigDecimal getEmployerSocialSecurityTax() {
            return employerSocialSecurityTax;
        }

        public void setEmployerSocialSecurityTax(BigDecimal employerSocialSecurityTax) {
            this.employerSocialSecurityTax = employerSocialSecurityTax;
        }

        public BigDecimal getEmployeeSocialSecurityTax() {
            return employeeSocialSecurityTax;
        }

        public void setEmployeeSocialSecurityTax(BigDecimal employeeSocialSecurityTax) {
            this.employeeSocialSecurityTax = employeeSocialSecurityTax;
        }

        public BigDecimal getMedicareTax() {
            return medicareTax;
        }

        public void setMedicareTax(BigDecimal medicareTax) {
            this.medicareTax = medicareTax;
        }

        public BigDecimal getAdditionalMedicareTax() {
            return additionalMedicareTax;
        }

        public void setAdditionalMedicareTax(BigDecimal additionalMedicareTax) {
            this.additionalMedicareTax = additionalMedicareTax;
        }
    }

    public static class Adjustments {
        private BigDecimal fractionOfCentAdjustment;
        private BigDecimal sickPayAdjustment;
        private BigDecimal otherAdjustments;

        // Default constructor for Jackson
        public Adjustments() {
        }

        @JsonCreator
        public Adjustments(
                @JsonProperty("fractionOfCentAdjustment") BigDecimal fractionOfCentAdjustment,
                @JsonProperty("sickPayAdjustment") BigDecimal sickPayAdjustment,
                @JsonProperty("otherAdjustments") BigDecimal otherAdjustments) {
            this.fractionOfCentAdjustment = fractionOfCentAdjustment;
            this.sickPayAdjustment = sickPayAdjustment;
            this.otherAdjustments = otherAdjustments;
        }

        public BigDecimal getFractionOfCentAdjustment() {
            return fractionOfCentAdjustment;
        }

        public void setFractionOfCentAdjustment(BigDecimal fractionOfCentAdjustment) {
            this.fractionOfCentAdjustment = fractionOfCentAdjustment;
        }

        public BigDecimal getSickPayAdjustment() {
            return sickPayAdjustment;
        }

        public void setSickPayAdjustment(BigDecimal sickPayAdjustment) {
            this.sickPayAdjustment = sickPayAdjustment;
        }

        public BigDecimal getOtherAdjustments() {
            return otherAdjustments;
        }

        public void setOtherAdjustments(BigDecimal otherAdjustments) {
            this.otherAdjustments = otherAdjustments;
        }
    }

    public static class Credits {
        private Boolean hasResearchCredit;
        private BigDecimal creditAmount;
        private Boolean form8974Attached;

        // Default constructor for Jackson
        public Credits() {
        }

        @JsonCreator
        public Credits(
                @JsonProperty("hasResearchCredit") Boolean hasResearchCredit,
                @JsonProperty("creditAmount") BigDecimal creditAmount,
                @JsonProperty("form8974Attached") Boolean form8974Attached) {
            this.hasResearchCredit = hasResearchCredit;
            this.creditAmount = creditAmount;
            this.form8974Attached = form8974Attached;
        }

        public Boolean getHasResearchCredit() {
            return hasResearchCredit;
        }

        public void setHasResearchCredit(Boolean hasResearchCredit) {
            this.hasResearchCredit = hasResearchCredit;
        }

        public BigDecimal getCreditAmount() {
            return creditAmount;
        }

        public void setCreditAmount(BigDecimal creditAmount) {
            this.creditAmount = creditAmount;
        }

        public Boolean getForm8974Attached() {
            return form8974Attached;
        }

        public void setForm8974Attached(Boolean form8974Attached) {
            this.form8974Attached = form8974Attached;
        }
    }

    public static class Deposits {
        private BigDecimal totalDeposits;
        private Boolean depositsComplete;
        private List<LocalDate> depositDates;

        // Default constructor for Jackson
        public Deposits() {
        }

        @JsonCreator
        public Deposits(
                @JsonProperty("totalDeposits") BigDecimal totalDeposits,
                @JsonProperty("depositsComplete") Boolean depositsComplete,
                @JsonProperty("depositDates") List<LocalDate> depositDates) {
            this.totalDeposits = totalDeposits;
            this.depositsComplete = depositsComplete;
            this.depositDates = depositDates;
        }

        public BigDecimal getTotalDeposits() {
            return totalDeposits;
        }

        public void setTotalDeposits(BigDecimal totalDeposits) {
            this.totalDeposits = totalDeposits;
        }

        public Boolean getDepositsComplete() {
            return depositsComplete;
        }

        public void setDepositsComplete(Boolean depositsComplete) {
            this.depositsComplete = depositsComplete;
        }

        public List<LocalDate> getDepositDates() {
            return depositDates;
        }

        public void setDepositDates(List<LocalDate> depositDates) {
            this.depositDates = depositDates;
        }
    }
}
