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
public class BranchUITests extends AbstractUITests {

    @Test
    public void testCreateBranch() {
        List<String> lines = FileLocatorUtils.readAllLines(this.getClass().getClassLoader().getResource("inputs/uitests/branch.csv"));
        List<BranchFormInputDto> branches = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length < 1)
                continue;
            String dataSetName = fields[0];
            String[] dataFields = Arrays.copyOfRange(fields, 0,fields.length);
            BranchFormInputDto branch = BranchFormInputDto.fromVerificationFields(dataFields);
            if (branch != null) {
                branches.add(branch);
            }
        }
        for (BranchFormInputDto branch : branches) {
            page.navigate(BASE_URL + "#/base/bankM/branch");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New Branch"))
                    .click();
            page.getByLabel("Branch Code", new Page.GetByLabelOptions().setExact(true)).fill(branch.getBranchCode());
            page.getByLabel("Branch Name").fill(branch.getBranchName());
            page.getByLabel("Account Id Code", new Page.GetByLabelOptions().setExact(true)).fill(branch.getAccountCode());

            page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Address")).click();
            Locator contactTabPanel = page.getByRole(AriaRole.TABPANEL, new Page.GetByRoleOptions().setName("Address"));
            contactTabPanel.getByLabel("Country Code").selectOption(branch.getCountryCode());
            contactTabPanel.getByLabel("State Code").selectOption(branch.getStateCode());
            contactTabPanel.getByLabel("City Code").selectOption(branch.getCityCode());
            contactTabPanel.getByLabel("Pin Code")
                    .fill(branch.getPinCode());
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
        }
        page.waitForURL(BASE_URL + "#/base/bankM/branch");

    }
}