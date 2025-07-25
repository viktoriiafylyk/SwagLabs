package com.swaglabs.tests.ui.testrunners;

import com.swaglabs.page.InventoryPage;
import com.swaglabs.page.LoginPage;
import com.swaglabs.tests.utils.LocalStorageJS;
import com.swaglabs.tests.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class BaseTestRunner {

    protected static LocalStorageJS localStorageJS;
    protected static WebDriver driver;
    protected static TestValueProvider testValueProvider;

    protected InventoryPage inventoryPage;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        testValueProvider = new TestValueProvider();
        initDriver();
    }

    @Step("init ChromeDriver")
    public void initDriver() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-features=PasswordManagerOnboarding,PasswordImport,AutofillServerCommunication,AutofillEnableAccountWalletStorage,OptimizationGuideModelDownloading");


        String chromeOptionsArg = System.getProperty("chrome.options", "");
        if (!chromeOptionsArg.isEmpty()) {
            for (String option : chromeOptionsArg.split(",")) {
                options.addArguments(option);
            }
        }

        String userDataDir = System.getProperty("user.data.dir");
        if (userDataDir != null && !userDataDir.isEmpty()) {
            options.addArguments("--user-data-dir=" + userDataDir);
        }

        String lang = System.getProperty("chrome.options", "--lang=en");
        if (lang != null && !lang.isEmpty()) {
            options.addArguments(lang);
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));

        localStorageJS = new LocalStorageJS(driver);
    }

    protected boolean shouldLoginBeforeTest() {
        return true;
    }

    @BeforeMethod
    public void beforeEachTest() {
        if (shouldLoginBeforeTest()) {
            driver.get(testValueProvider.getBaseUIUrl());

            String username = testValueProvider.getUserEmail();
            String password = testValueProvider.getUserName();

            LoginPage loginPage = new LoginPage(driver);
            inventoryPage = loginPage.login(username, password);
        }
    }


    @BeforeClass
    public void beforeClass() {
        if (driver == null) {
            initDriver();
        }

        driver.get(testValueProvider.getBaseUIUrl());
    }

    @AfterClass()
    public void afterClass(ITestContext context) {
        takeScreenShot("PICTURE Test Name = " + context.getName());
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

    @AfterSuite
    public void afterSuite() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

    @Step("Clear Browser Memory Cookies and LocalStorage.")
    public void clearBrowserMemory() {
        driver.manage().deleteAllCookies();
        localStorageJS.clearLocalStorage();
        driver.navigate().refresh();
    }

    @Attachment(value = "Image name = {0}", type = "image/png")
    public byte[] takeScreenShot(String attachName) {
        byte[] result = null;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            result = Files.readAllBytes(scrFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
