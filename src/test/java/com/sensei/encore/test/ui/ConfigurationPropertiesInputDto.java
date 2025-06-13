package com.sensei.encore.test.ui;

import lombok.Data;

@Data
public class ConfigurationPropertiesInputDto {

    private String moduleCode;
    private String propertyName;
    private String propertyValue;

    public static ConfigurationPropertiesInputDto fromVerificationFields(String[] fields) {
        if (fields.length != 3)
            return null;
        int i = 0;
        ConfigurationPropertiesInputDto propertiesInputDto  = new ConfigurationPropertiesInputDto();
        propertiesInputDto.setModuleCode(fields[i++]);
        propertiesInputDto.setPropertyName(fields[i++]);
        propertiesInputDto.setPropertyValue(fields[i++]);
        return propertiesInputDto;
    }
}
