package com.sensei.encore.test.ui;

import lombok.Data;

@Data
public class BranchFormInputDto {

    private String branchCode;
    private String branchName;
    private String accountCode;
    private String countryCode;
    private String stateCode;
    private String cityCode;
    private String pinCode;

    public static BranchFormInputDto fromVerificationFields(String[] fields) {
        if (fields.length != 7)
            return null;
        int i = 0;
        BranchFormInputDto branch  = new BranchFormInputDto();
        branch.setBranchCode(fields[i++]);
        branch.setBranchName(fields[i++]);
        branch.setAccountCode(fields[i++]);
        branch.setCountryCode(fields[i++]);
        branch.setStateCode(fields[i++]);
        branch.setCityCode(fields[i++]);
        branch.setPinCode(fields[i++]);
        return branch;
    }
}