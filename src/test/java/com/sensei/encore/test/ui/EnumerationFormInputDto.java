package com.sensei.encore.test.ui;

import lombok.Data;

@Data
public class EnumerationFormInputDto {

    private String classifier;
    private String sortOrder;
    private String code;
    private String name;

    public static EnumerationFormInputDto fromVerificationFields(String[] fields) {
        if (fields.length != 4)
            return null;
        int i = 0;
        EnumerationFormInputDto enumeration  = new EnumerationFormInputDto();
        enumeration.setClassifier(fields[i++]);
        enumeration.setSortOrder(fields[i++]);
        enumeration.setCode(fields[i++]);
        enumeration.setName(fields[i++]);
        return enumeration;
    }
}