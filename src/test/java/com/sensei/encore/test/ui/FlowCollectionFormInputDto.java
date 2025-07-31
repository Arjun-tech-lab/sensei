package com.sensei.encore.test.ui;
import lombok.Data;
@Data

public class FlowCollectionFormInputDto {

    private String remarks;
    private String instrument;
    private String PaidAmount;

    public static FlowCollectionFormInputDto fromVerificationFields(String[] fields) {
        if (fields.length != 3)
            return null;
        int i = 0;
        FlowCollectionFormInputDto flow = new FlowCollectionFormInputDto();
        flow.setInstrument(fields[i++]);
        flow.setPaidAmount(fields[i++]);
        flow.setRemarks(fields[i++]);
        return flow;


    }
}


