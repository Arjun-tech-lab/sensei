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
public class LoanActionsUITest extends AbstractUITests {

    @Test
    public void testDisburse() {
        page.navigate(BASE_URL + "#/loans-account/loan-accounts");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Actions expand_more")).first().click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Disburse")).click();
        page.getByLabel("Instrument", new Page.GetByLabelOptions().setExact(true)).selectOption("1: CASH");
         page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Disbursement Amount")).fill("86959");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View"));
        page.pause();
    }
    @Test
    public void testRepay() {
        page.navigate(BASE_URL + "#/loans-account/loan-accounts");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Actions expand_more")).first().click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Repay")).hover();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Scheduled Repayment")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Remarks")).fill("Paid");
        page.getByLabel("Instrument").selectOption("CASH");
        page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Paid Amount")).fill("1000");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();
    }
    @Test
    public void testChargeFees() {
        page.navigate(BASE_URL + "#/loans-account/loan-accounts");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Actions expand_more")).first().click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Charge Fees")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Fee Date")).fill("2002-01-01");
    }
    @Test
    public void testReverse() {
        page.navigate(BASE_URL + "#/loans-account/loan-accounts");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Actions expand_more")).nth(2).first().click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Reverse")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Reverse")).nth(1).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("field_reversalRemarks")).fill("Reversed Successfully");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("close")).click();
    }
    @Test
    public void testSetStatus() {
        page.navigate(BASE_URL + "#/loans-account/loan-accounts");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Actions expand_more")).nth(1).click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Set Status")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();

    }
    @Test
    public void testSimulate() {
        page.navigate(BASE_URL + "#/loans-account/loan-accounts");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Actions expand_more")).nth(0).click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Simulate")).hover();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Simulate Disbursement")).click();
        page.getByLabel("Instrument", new Page.GetByLabelOptions().setExact(true)).selectOption("3: ELECTRONIC");
        page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Disbursement Amount")).fill("86959");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Run Simulation")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Back")).click();
    }
}