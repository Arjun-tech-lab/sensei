package com.sensei.encore.test.ui;

import lombok.Data;

@Data
public class CustomerFormInputDto {

    private String customerId;
    private String branchCode;
    private String dateOfBirth;
    private String firstName;
    private String lastName;
    private String salutation;
    private String gender;
    private String customerType;
    private String customerStatus;
    private String pan;
    private String phone;
    private String email;
    private String uid;
    private String address1;
    private String address2;
    private String pinCode;
    private String addressType1;

    public static CustomerFormInputDto fromVerificationFields(String[] fields) {
        if (fields.length != 17)
            return null;
        int i = 0;
        CustomerFormInputDto customer  = new CustomerFormInputDto();
        customer.setCustomerId(fields[i++]);
        customer.setBranchCode(fields[i++]);
        customer.setDateOfBirth(fields[i++]);
        customer.setFirstName(fields[i++]);
        customer.setLastName(fields[i++]);
        customer.setSalutation(fields[i++]);
        customer.setGender(fields[i++]);
        customer.setCustomerType(fields[i++]);
        customer.setCustomerStatus(fields[i++]);
        customer.setPan(fields[i++]);
        customer.setPhone(fields[i++]);
        customer.setEmail(fields[i++]);
        customer.setUid(fields[i++]);
        customer.setAddress1(fields[i++]);
        customer.setAddress2(fields[i++]);
        customer.setPinCode(fields[i++]);
        customer.setAddressType1(fields[i++]);
        return customer;
    }
}
