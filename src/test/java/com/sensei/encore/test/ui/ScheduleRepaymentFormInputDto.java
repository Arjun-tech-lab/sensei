package com.sensei.encore.test.ui;
import lombok.Data;


@Data

public class ScheduleRepaymentFormInputDto {
    private String remarks;
    private String instrument;
    private String balanceAmount;
    private String PaidAmount;

    public static ScheduleRepaymentFormInputDto fromVerificationFields(String[] fields) {
        if (fields.length != 4)
            return null;
        int i = 0;
        ScheduleRepaymentFormInputDto repay=new ScheduleRepaymentFormInputDto();
        repay.setRemarks(fields[i++]);
        repay.setInstrument(fields[i++]);
        repay.setPaidAmount(fields[i++]);
        repay.setBalanceAmount(fields[i++]);

        return repay;


    }
}
