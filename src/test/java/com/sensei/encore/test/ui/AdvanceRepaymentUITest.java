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


public class AdvanceRepaymentUITest extends AbstractUITests {
    @Test


    public void TestAdvanceRepayment()


    {

        List<String> lines = FileLocatorUtils.readAllLines(this.getClass().getClassLoader().getResource("inputs/uitests/ScheduleRepayment.csv"));
        List<AdvanceRepaymentFormInputdto> Advance = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length < 1)
                continue;
            String[] dataFields = Arrays.copyOfRange(fields, 0, fields.length);
            AdvanceRepaymentFormInputdto advance = AdvanceRepaymentFormInputdto.fromVerificationFields(dataFields);
            if (advance != null) {
                Advance.add(advance);
            }
        }

        for (AdvanceRepaymentFormInputdto advance : Advance) {
            page.navigate("https://encore-demo.senseitech.com/encore-client/#/loans-account/loan-accounts/46387346");
            Locator customerRow = page.locator("tr:has-text('₹500,000.00')");
            Locator actionsButton = customerRow.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Actions"));
            actionsButton.click();

            Locator repayItem = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Repay"));
            repayItem.click();

            Locator AdvancedRepayment = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Advance Repayment"));
            AdvancedRepayment.click();

            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Remarks")).fill(advance.getRemarks());

            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Instrument")).selectOption(advance.getInstrument());

            page.getByLabel("Paid Amount").fill(advance.getPaidAmount());


            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();

            page.locator("#view_btn").click();


        }
        page.waitForURL("https://encore-demo.senseitech.com/encore-client/#/loans-account/loan-accounts/46387346?view=007369026");
        page.pause();

    }
}