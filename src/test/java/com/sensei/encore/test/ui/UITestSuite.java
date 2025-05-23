package com.sensei.encore.test.ui;

import com.microsoft.playwright.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Paths;

public class UITestSuite {

    public static final String TESTAUTH = ".testauth/";
    public static final String USER_ID = "shridhar";
    public static final String PASSWORD = "Admin@123";
    public static final String BASE_URL = "https://encore-demo-env.senseitech.com/clientencore/";
    // Shared between all tests in this class.
    protected Playwright playwright;
    protected Browser browser;
    protected String authFileName = "." + RandomStringUtils.randomAlphanumeric(8);

    // New instance for each test method.
    protected BrowserContext context;
    protected Page page;

    @BeforeSuite
    void launchBrowserCreateContextAndPage() {
        playwright = Playwright.create();
        //browser = playwright.chromium().launch();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
        if (Paths.get(TESTAUTH + authFileName).toFile().exists())
            context = browser.newContext(
                    new Browser.NewContextOptions().setStorageStatePath(Paths.get(TESTAUTH + authFileName)));
        else
            context = browser.newContext();
        page = context.newPage();
    }

    @AfterSuite
    void closeContext() {
        context.close();
        playwright.close();
        Paths.get(TESTAUTH + authFileName).toFile().delete();
    }

}
