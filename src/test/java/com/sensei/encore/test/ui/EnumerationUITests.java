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
public class EnumerationUITests extends AbstractUITests {

    @Test
    public void testCreateEnumeration() {
        List<String> lines = FileLocatorUtils.readAllLines(this.getClass().getClassLoader().getResource("inputs/uitests/enumeration.csv"));
        List<EnumerationFormInputDto> enumerations = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length < 1)
                continue;
            String dataSetName = fields[0];
            String[] dataFields = Arrays.copyOfRange(fields, 1, fields.length);
            EnumerationFormInputDto enumeration = EnumerationFormInputDto.fromVerificationFields(dataFields);
            if (enumeration != null) {
                enumerations.add(enumeration);
            }
        }
        for (EnumerationFormInputDto enumeration : enumerations) {
            page.navigate(BASE_URL + "#/base/refCodes/enumerations");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New Enumeration"))
                    .click();
            page.getByLabel("Classifier").selectOption(enumeration.getClassifier());
            page.getByLabel("Sort Order").fill(enumeration.getSortOrder());
            page.getByLabel("Code", new Page.GetByLabelOptions().setExact(true)).fill(enumeration.getCode());
            page.getByLabel("Name").fill(enumeration.getName());
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"))
                    .click();
        }
        page.pause();
    }
    @Test
    public void testSearchFilter() {
        page.navigate(BASE_URL + "#/base/refCodes/enumerations");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Show Filters"))
                .click();
        page.getByPlaceholder("Classifier equals").selectOption("AssetCategory");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search"))
                .click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear"))
                .click();
        page.getByPlaceholder("Code").fill("def");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search"))
                .click();
    }
    @Test
    public void testEditEnumeration(){
        page.navigate(BASE_URL + "#/base/refCodes/enumerations");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit")).nth(0)
                .click();
        page.getByLabel("Classifier").selectOption("Collateral");
        page.getByLabel("Sort Order").fill("25");
        page.getByLabel("Code", new Page.GetByLabelOptions().setExact(true)).fill("AbCdE");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"))
                .click();
    }
    @Test
    public void testDeleteEnumeration(){
        page.navigate(BASE_URL + "#/base/refCodes/enumerations");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).nth(0)
                .click();
    }
}