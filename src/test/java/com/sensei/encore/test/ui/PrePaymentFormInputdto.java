package com.sensei.encore.test.ui;
import lombok.Data;
@Data

public class PrePaymentFormInputdto {
    private String instrument;
    private String remarks ;

    private String paidAmount ;

    public static PrePaymentFormInputdto fromVerificationFields(String[] fields) {
        if (fields.length != 3)
            return null;
        int i = 0;
        PrePaymentFormInputdto prepay = new PrePaymentFormInputdto();
        prepay.setInstrument(fields[i++]);
        prepay.setRemarks(fields[i++]);
        prepay.setPaidAmount(fields[i++]);
        return prepay;
    }
}
