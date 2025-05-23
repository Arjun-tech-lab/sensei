package com.sensei.encore.test.ui;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
@SpringBootTest
public class AbstractUITests extends UITestSuite {

    @Test (priority = -1)
    public void testLogin() {
        page.navigate(BASE_URL);
        //page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
        // Expect a title "to contain" a substring.
        assertThat(page).hasTitle(Pattern.compile("Encore Banking"));

        page.getByPlaceholder("Your username").fill(USER_ID);
        page.getByPlaceholder("Your password").fill("Wrong-" + PASSWORD);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in"))
                .click();
        assertThat(page.getByText("Failed to sign in")).isVisible();
        page.getByPlaceholder("Your username").fill(USER_ID);
        page.getByPlaceholder("Your password").fill(PASSWORD);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in"))
                .click();
        assertThat(page.getByText("Welcome to Encore")).isVisible();
        // Save storage state into the file.
        context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(TESTAUTH + authFileName)));
    }

}
