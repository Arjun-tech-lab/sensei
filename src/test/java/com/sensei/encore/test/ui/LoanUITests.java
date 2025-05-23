package com.sensei.encore.test.ui;

import com.sensei.encore.test.utils.FileLocatorUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class LoanUITests extends AbstractUITests {

    @Test
    public void testCreateLoanAccount() {
        List<String> lines = FileLocatorUtils.readAllLines(this.getClass().getClassLoader().getResource("inputs/uitests/loans.csv"));
        List<LoanFormInputDto> loans = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length < 1)
                continue;
            String[] dataFields = Arrays.copyOfRange(fields, 0, fields.length);
            LoanFormInputDto loan = LoanFormInputDto.fromVerificationFields(dataFields);
            if (loan != null) {
                loans.add(loan);
            }
        }
        for(LoanFormInputDto loan : loans) {
            page.navigate(BASE_URL + "#/loans-account/loan-accounts?type=create");
            page.getByLabel("Product Code").selectOption(loan.getProductCode());
            page.getByLabel("Branch Code").selectOption(loan.getBranchCode());
            page.getByLabel("Customer Id").fill(loan.getCustomerId());
            page.getByLabel("Account Open Date").fill(loan.getAccountOpenDate());
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed"))
                    .click();

            page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Terms")).click();
            Locator termPanel = page.getByRole(AriaRole.TABPANEL, new Page.GetByRoleOptions().setName("Terms"));
            termPanel.getByLabel("Amount", new Locator.GetByLabelOptions().setExact(true)).fill(loan.getAmount());
            termPanel.getByLabel("Tenure Period").fill(loan.getTenure());

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();

        }
        page.waitForURL(BASE_URL + "#/loans-account/loan-accounts");
        page.pause();
    }
}
