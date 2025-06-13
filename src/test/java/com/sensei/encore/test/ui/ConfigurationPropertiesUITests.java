package com.sensei.encore.test.ui;

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
public class ConfigurationPropertiesUITests extends AbstractUITests {

    @Test
    public void testCreateConfigurationProperty() {
        List<String> lines = FileLocatorUtils.readAllLines(this.getClass().getClassLoader().getResource("inputs/uitests/configurationProperties.csv"));
        List<ConfigurationPropertiesInputDto> configurationPropertiesInputDtos = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length < 1)
                continue;
            String[] dataFields = Arrays.copyOfRange(fields, 0, fields.length);
            ConfigurationPropertiesInputDto propertiesInputDto = ConfigurationPropertiesInputDto.fromVerificationFields(dataFields);
            if (propertiesInputDto != null) {
                configurationPropertiesInputDtos.add(propertiesInputDto);
            }
        }
        for(ConfigurationPropertiesInputDto propertiesInputDto : configurationPropertiesInputDtos) {
            page.navigate(BASE_URL + "#/base/refCodes/properties");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New Property"))
                    .click();
            page.getByLabel("Module Code").selectOption(propertiesInputDto.getModuleCode());
            page.getByLabel("Property Name").fill(propertiesInputDto.getPropertyName());
            page.getByLabel("Property Value").fill(propertiesInputDto.getPropertyValue());
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"))
                    .click();

        }
        page.waitForURL(BASE_URL + "#/base/refCodes/properties");
        page.pause();
    }

    @Test
    public void testEditConfigurationProperty() {
        page.navigate(BASE_URL + "#/base/refCodes/properties");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit")).nth(0)
                .click();
        page.getByLabel("Property Value").fill("false");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"))
                    .click();

    }

    @Test
    public void testDeleteConfigurationProperty() {
        page.navigate(BASE_URL + "#/base/refCodes/properties");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).nth(0)
                .click();

    }

    @Test
    public void testCheckSearchFilterConfigurationProperty() {
        page.navigate(BASE_URL + "#/base/refCodes/properties");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Show Filter"))
                .click();
        page.getByPlaceholder("Module Code equals").selectOption("Base");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search"))
                .click();
    }
}
