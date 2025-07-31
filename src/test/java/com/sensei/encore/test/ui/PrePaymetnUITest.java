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



public class PrePaymetnUITest extends AbstractUITests {
    @Test

    public void testPrepayement(){
        List<String> lines = FileLocatorUtils.readAllLines(this.getClass().getClassLoader().getResource("inputs/uitests/PrePayment.csv"));
        List<PrePaymentFormInputdto> Prepayment= new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length < 1)
                continue;
            String[] dataFields = Arrays.copyOfRange(fields, 0, fields.length);
            PrePaymentFormInputdto prepay = PrePaymentFormInputdto.fromVerificationFields(dataFields);
            if (prepay != null) {
                Prepayment.add(prepay);
            }
        }

        for (PrePaymentFormInputdto prepay : Prepayment){
            page.navigate("https://encore-demo.senseitech.com/encore-client/#/loans-account/loan-accounts/46387346");
            Locator customerRow = page.locator("tr:has-text('₹500,000.00')");
            Locator actionsButton = customerRow.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Actions"));
            actionsButton.click();

            Locator repayItem = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Repay"));
            repayItem.click();

            Locator prepayment = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("PrePayment"));
            prepayment.click();

            page.waitForURL("https://encore-demo.senseitech.com/encore-client/#/loans-account/loan-accounts/46387346?prepayment=007369026");

            page.getByLabel("Instrument").selectOption(prepay.getInstrument());



            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Remarks")).fill(prepay.getRemarks());


            page.getByLabel("Paid Amount").fill(prepay.getPaidAmount());



            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();

            page.locator("#view_btn").click();




        }


    }

}
