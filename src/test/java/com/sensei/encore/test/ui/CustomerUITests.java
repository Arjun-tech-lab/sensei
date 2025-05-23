package com.sensei.encore.test.ui;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.sensei.encore.test.utils.FileLocatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class CustomerUITests extends AbstractUITests {

    @Test
    public void testCreateCustomers() {
        List<String> lines = FileLocatorUtils.readAllLines(this.getClass().getClassLoader().getResource("inputs/uitests/customers.csv"));
        List<CustomerFormInputDto> customers = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length < 1)
                continue;
            String dataSetName = fields[0];
            String[] dataFields = Arrays.copyOfRange(fields, 1, fields.length);
            CustomerFormInputDto customer = CustomerFormInputDto.fromVerificationFields(dataFields);
            if (customer != null) {
                customers.add(customer);
            }
        }
        for(CustomerFormInputDto customer : customers) {
            page.navigate(BASE_URL + "#/customer/profile?type=create");
            page.getByLabel("Customer Id", new Page.GetByLabelOptions().setExact(true)).fill(customer.getCustomerId());
            page.getByLabel("Branch Code").selectOption(customer.getBranchCode());
            page.getByLabel("Date of Birth").fill(customer.getDateOfBirth());
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed"))
                    .click();

            page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Basic")).click();
            Locator basicTabPanel = page.getByRole(AriaRole.TABPANEL, new Page.GetByRoleOptions().setName("Basic"));
            basicTabPanel.getByLabel("Customer First Name").fill(customer.getFirstName());
            basicTabPanel.getByLabel("Customer Last Name").fill(customer.getLastName());
            basicTabPanel.getByLabel("Salutation").selectOption(customer.getSalutation());
            basicTabPanel.getByLabel("Gender").selectOption(customer.getGender());

            page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Identification")).click();
            Locator identificationTabPanel = page.getByRole(AriaRole.TABPANEL, new Page.GetByRoleOptions().setName("Identification"));
            identificationTabPanel.getByLabel("PAN").fill(customer.getPan());
            identificationTabPanel.getByLabel("UID").fill(customer.getUid());

            page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Contact")).click();
            Locator contactTabPanel = page.getByRole(AriaRole.TABPANEL, new Page.GetByRoleOptions().setName("Contact"));
            contactTabPanel.getByLabel("Address1").nth(0).fill(customer.getAddress1());
            contactTabPanel.getByLabel("Address2").nth(0).fill(customer.getAddress2());
            contactTabPanel.getByLabel("Pin Code").nth(0).fill(customer.getPinCode());
            contactTabPanel.getByRole(AriaRole.BUTTON).getByText("Lookup").nth(0).click();
            contactTabPanel.getByLabel("Address Type").nth(0).selectOption(customer.getAddressType1());
            contactTabPanel.getByLabel("Phone1").nth(0).fill(customer.getPhone());
            contactTabPanel.getByLabel("Email").nth(0).fill(customer.getEmail());
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
            page.waitForURL(BASE_URL + "#/customer/profile");
        }
    }

    @Test
    public void testSearchCustomers() {
        page.navigate(BASE_URL + "#/customer/profile");
        String rowCountStr = page.getByLabel("Profile Table").getAttribute("aria-rowcount");
        int rowCount = Integer.parseInt(rowCountStr);
        org.assertj.core.api.AssertionsForClassTypes.assertThat(rowCount).isEqualTo(2);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Show Filters"))
                .click();
        page.getByPlaceholder("Enter Customer ID").fill("0001");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply Filters"))
                .click();
        rowCountStr = page.getByLabel("Profile Table").getAttribute("aria-rowcount");
        rowCount = Integer.parseInt(rowCountStr);
        org.assertj.core.api.AssertionsForClassTypes.assertThat(rowCount).isEqualTo(1);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear Filters"))
                .click();
        page.getByPlaceholder("Enter Pan no").fill("BBBBB1111B");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply Filters"))
                .click();
        rowCountStr = page.getByLabel("Profile Table").getAttribute("aria-rowcount");
        rowCount = Integer.parseInt(rowCountStr);
        org.assertj.core.api.AssertionsForClassTypes.assertThat(rowCount).isEqualTo(1);
    }

}
