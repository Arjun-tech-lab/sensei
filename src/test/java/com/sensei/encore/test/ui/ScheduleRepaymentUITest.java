package com.sensei.encore.test.ui;
import com.microsoft.playwright.Locator;
import com.sensei.encore.test.utils.FileLocatorUtils;


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

public class ScheduleRepaymentUITest extends AbstractUITests {
    @Test

    public void TestSchdeuleRepayment() {
        List<String> lines = FileLocatorUtils.readAllLines(this.getClass().getClassLoader().getResource("inputs/uitests/ScheduleRepayment.csv"));
        List<ScheduleRepaymentFormInputDto> Repayment = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length < 1)
                continue;
            String[] dataFields = Arrays.copyOfRange(fields, 0, fields.length);
            ScheduleRepaymentFormInputDto repay = ScheduleRepaymentFormInputDto.fromVerificationFields(dataFields);
            if (repay != null) {
                Repayment.add(repay);
            }
        }




        for (ScheduleRepaymentFormInputDto repay : Repayment) {


            page.navigate(BASE_URL + "#/loans-account/loan-accounts");
            page.locator("tr", new Page.LocatorOptions().setHasText("1001Pro00005"))
                    .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("View"))
                    .click();


            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Schedule")).click();
            String statusText = page.locator("span.text-red-600").textContent().trim();
            if (statusText.equals("Due")) {

                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Actions")).click();
                page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Repay")).hover();
                page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Scheduled Repayment")).click();
                page.getByLabel("Repayment Date").fill(repay.getRePaymentdate());
                page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Remarks")).fill(repay.getRemarks());

                page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Instrument")).selectOption(repay.getInstrument());

                page.getByLabel("Paid Amount").fill(repay.getPaidAmount());
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();

                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();

                page.locator("#view_btn").click();
            }


        }
        page.navigate(BASE_URL+"#/loans-account/loan-accounts?view=1001Pro00005");
        ScheduleRepaymentFormInputDto repay = Repayment.get(0);
        page.reload();
        String priceText = page.locator(".text-green-700").nth(1).textContent();
        double price = Double.parseDouble(priceText.replaceAll("[^\\d.]", "").replace(",", ""));

        org.assertj.core.api.Assertions.assertThat(price).isEqualTo(repay.getBalanceAmount());


    }
}