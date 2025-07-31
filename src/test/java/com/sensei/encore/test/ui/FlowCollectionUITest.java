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

public class FlowCollectionUITest extends AbstractUITests {
    @Test

    public void TestFlowControl() {
        List<String> lines = FileLocatorUtils.readAllLines(this.getClass().getClassLoader().getResource("inputs/uitests/FlowCollection.csv"));
        List<FlowCollectionFormInputDto> Flow = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length < 1)
                continue;
            String[] dataFields = Arrays.copyOfRange(fields, 0, fields.length);
            FlowCollectionFormInputDto flow = FlowCollectionFormInputDto.fromVerificationFields(dataFields);
            if (flow != null) {
                Flow.add(flow);
            }
        }
        for (FlowCollectionFormInputDto flow : Flow) {
            page.navigate("https://encore-demo.senseitech.com/encore-client/#/loans-account/loan-accounts/46387346");
            Locator customerRow = page.locator("tr:has-text('₹500,000.00')");
            Locator actionsButton = customerRow.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Actions"));
            actionsButton.click();

            Locator repayItem = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Repay"));
            repayItem.click();

            Locator scheduledRepayment = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Flow Collection"));
            scheduledRepayment.click();

            page.waitForURL("https://encore-demo.senseitech.com/encore-client/#/loans-account/loan-accounts/46387346?flowCollectionRepayment=007369026");

            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Remarks")).fill(flow.getRemarks());

            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Instrument")).selectOption(flow.getInstrument());

            page.getByLabel("Paid Amount").fill(flow.getPaidAmount());


            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();

            page.locator("#view_btn").click();


        }
        page.waitForURL("https://encore-demo.senseitech.com/encore-client/#/loans-account/loan-accounts/46387346?view=007369026");
        page.pause();

    }

}


