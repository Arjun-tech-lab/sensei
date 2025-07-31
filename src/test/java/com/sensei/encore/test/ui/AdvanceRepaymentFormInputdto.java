package com.sensei.encore.test.ui;
import lombok.Data;


@Data

public class AdvanceRepaymentFormInputdto {
    private String remarks;
    private String instrument;

    private String PaidAmount;

    public static AdvanceRepaymentFormInputdto fromVerificationFields(String[] fields) {
        if (fields.length != 3)
            return null;
        int i = 0;
        AdvanceRepaymentFormInputdto advance=new AdvanceRepaymentFormInputdto();
        advance.setRemarks(fields[i++]);
        advance.setInstrument(fields[i++]);
        advance.setPaidAmount(fields[i++]);
        return advance;


    }
}
