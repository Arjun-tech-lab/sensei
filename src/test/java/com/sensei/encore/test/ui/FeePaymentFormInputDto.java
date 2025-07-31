package com.sensei.encore.test.ui;
import lombok.Data;
@Data
public class FeePaymentFormInputDto {
    private String repaymentDate;
    private String remarks;
    private String instrument;
    private String feePayment;
    private String deduction;


    public static FeePaymentFormInputDto fromVerificationFields(String[] fields) {
        if (fields.length != 5)
            return null;
        int i = 0;
        FeePaymentFormInputDto fee = new FeePaymentFormInputDto();
        fee.setRemarks(fields[i++]);
        fee.setInstrument(fields[i++]);
        fee.setRepaymentDate(fields[i++]);
        fee.setFeePayment(fields[i++]);
        fee.setDeduction(fields[i++]);
        return fee;

    }
}