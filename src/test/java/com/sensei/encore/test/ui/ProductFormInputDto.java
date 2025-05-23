package com.sensei.encore.test.ui;

import lombok.Data;

@Data
public class ProductFormInputDto {

    private String productCode;
    private String currency;
    private String amountFrom;
    private String amountTo;
    private String tenureFrom;
    private String tenureTo;
    private String frequencyUnit;
    private String branchSetCode;
    private String accountIdCode;
    private String category;
    private String accountIdGenerationRuleCode;
    private String normalInterestRateBasis;
    private String penalInterestRateBasis;

    private String interestReceivableAccountCode;
    private String interestIncomeAccountCode;
    private String interestAccruedAccountCode;
    private String interestSuspenseAccountCode;
    private String normalInterestWriteOffAccountCode;
    private String normalInterestWaiverAccountCode;
    private String normalInterestIncomeBookedNpaAccountCode;
    private String normalInterestIncomeRepaidNpaAccountCode;
    private String interestTdsAccountCode;

    private String penalInterestReceivableAccountCode;
    private String penalInterestIncomeAccountCode;
    private String penalInterestAccruedAccountCode;
    private String penalInterestSuspenseAccountCode;
    private String penalInterestWriteOffAccountCode;
    private String penalInterestWaiverAccountCode;
    private String penalInterestIncomeBookedNpaAccountCode;
    private String penalInterestIncomeRepaidNpaAccountCode;

    private String additionalInterestReceivableAccountCode;
    private String additionalInterestIncomeAccountCode;
    private String additionalInterestAccruedAccountCode;
    private String additionalInterestSuspenseAccountCode;
    private String additionalInterestWriteOffAccountCode;
    private String additionalInterestWaiverAccountCode;
    private String additionalInterestIncomeBookedNpaAccountCode;
    private String additionalInterestIncomeRepaidNpaAccountCode;

    private String loanLossProvisionReserveAccountCode;
    private String loanLossProvisionStdExpenseAccountCode;
    private String loanLossProvisionStdIncomeAccountCode;
    private String loanLossProvisionNpaExpenseAccountCode;
    private String loanLossProvisionNpaIncomeAccountCode;

    private String tax1AccountCode;
    private String tax2AccountCode;
    private String tax3AccountCode;
    private String tax4AccountCode;

    private String forfeitureAccountCode;
    private String waiverAccountCode;
    private String writeOffRecoveryAccountCode;

    private String principalWriteOffAccountCode;
    private String principalContraAccountCode;
    private String principalReceivableAccountCode;
    private String principalControlAccountCode;

    private String glSubHead;
    private String securityDepositAccountCode;
    private String securityDepositAdvanceInterestAccountCode;






    public static ProductFormInputDto fromVerificationFields(String[] fields) {
//        if (fields.length != 14)
//            return null;
        int i = 0;
        ProductFormInputDto product  = new ProductFormInputDto();
        product.setProductCode(fields[i++]);
        product.setCurrency(fields[i++]);
        product.setAmountFrom(fields[i++]);
        product.setAmountTo(fields[i++]);
        product.setTenureFrom(fields[i++]);
        product.setTenureTo(fields[i++]);
        product.setFrequencyUnit(fields[i++]);
        product.setBranchSetCode(fields[i++]);
        product.setAccountIdCode(fields[i++]);
        product.setCategory(fields[i++]);
        product.setAccountIdGenerationRuleCode(fields[i++]);
        product.setNormalInterestRateBasis(fields[i++]);
        product.setPenalInterestRateBasis(fields[i++]);

        product.setInterestReceivableAccountCode(fields[i]);
        product.setInterestIncomeAccountCode(fields[i]);
        product.setInterestAccruedAccountCode(fields[i]);
        product.setInterestSuspenseAccountCode(fields[i]);
        product.setNormalInterestWriteOffAccountCode(fields[i]);
        product.setNormalInterestWaiverAccountCode(fields[i]);
        product.setNormalInterestIncomeBookedNpaAccountCode(fields[i]);
        product.setNormalInterestIncomeRepaidNpaAccountCode(fields[i]);
        product.setInterestTdsAccountCode(fields[i]);

        product.setPenalInterestReceivableAccountCode(fields[i]);
        product.setPenalInterestIncomeAccountCode(fields[i]);
        product.setPenalInterestAccruedAccountCode(fields[i]);
        product.setPenalInterestSuspenseAccountCode(fields[i]);
        product.setPenalInterestWriteOffAccountCode(fields[i]);
        product.setPenalInterestWaiverAccountCode(fields[i]);
        product.setPenalInterestIncomeBookedNpaAccountCode(fields[i]);
        product.setPenalInterestIncomeRepaidNpaAccountCode(fields[i]);

        product.setAdditionalInterestReceivableAccountCode(fields[i]);
        product.setAdditionalInterestIncomeAccountCode(fields[i]);
        product.setAdditionalInterestAccruedAccountCode(fields[i]);
        product.setAdditionalInterestSuspenseAccountCode(fields[i]);
        product.setAdditionalInterestWriteOffAccountCode(fields[i]);
        product.setAdditionalInterestWaiverAccountCode(fields[i]);
        product.setAdditionalInterestIncomeBookedNpaAccountCode(fields[i]);
        product.setAdditionalInterestIncomeRepaidNpaAccountCode(fields[i]);

        product.setLoanLossProvisionReserveAccountCode(fields[i]);
        product.setLoanLossProvisionStdExpenseAccountCode(fields[i]);
        product.setLoanLossProvisionStdIncomeAccountCode(fields[i]);
        product.setLoanLossProvisionNpaExpenseAccountCode(fields[i]);
        product.setLoanLossProvisionNpaIncomeAccountCode(fields[i]);

        product.setTax1AccountCode(fields[i]);
        product.setTax2AccountCode(fields[i]);
        product.setTax3AccountCode(fields[i]);
        product.setTax4AccountCode(fields[i]);

        product.setForfeitureAccountCode(fields[i]);
        product.setWaiverAccountCode(fields[i]);
        product.setWriteOffRecoveryAccountCode(fields[i]);

        product.setPrincipalWriteOffAccountCode(fields[i]);
        product.setPrincipalContraAccountCode(fields[i]);
        product.setPrincipalReceivableAccountCode(fields[i]);
        product.setPrincipalControlAccountCode(fields[i]);
        product.setSecurityDepositAccountCode(fields[i]);
        product.setSecurityDepositAdvanceInterestAccountCode(fields[i]);

        product.setGlSubHead(fields[++i]);


        return product;
    }
}
