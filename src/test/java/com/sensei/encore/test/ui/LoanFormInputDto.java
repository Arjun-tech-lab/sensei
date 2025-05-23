package com.sensei.encore.test.ui;

import lombok.Data;

@Data
public class LoanFormInputDto {

    private String productCode;
    private String branchCode;
    private String accountOpenDate;
    private String customerId;
    private String amount;
    private String tenure;

    public static LoanFormInputDto fromVerificationFields(String[] fields) {
        if (fields.length != 6)
            return null;
        int i = 0;
        LoanFormInputDto loan  = new LoanFormInputDto();
        loan.setProductCode(fields[i++]);
        loan.setBranchCode(fields[i++]);
        loan.setAccountOpenDate(fields[i++]);
        loan.setCustomerId(fields[i++]);
        loan.setAmount(fields[i++]);
        loan.setTenure(fields[i++]);
        return loan;
    }
}
