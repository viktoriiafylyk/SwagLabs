package com.swaglabs.tests.ui;

import com.swaglabs.tests.ui.testrunners.BaseTestRunner;
import com.swaglabs.tests.dataprovider.InvalidLoginDataProvider;
import com.swaglabs.page.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TestInvalidLogin extends BaseTestRunner {
    @Override
    protected boolean shouldLoginBeforeTest() {
        return false;
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = InvalidLoginDataProvider.class)
    @Description("Verify login with invalid data.")
    @Feature("Login with invalid data")
    @Issue("2")
    @Owner("Fylyk Viktoriia")
    public void testInvalidLogin (String username, String password){
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        softAssert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for invalid credentials");
        softAssert.assertTrue(loginPage.getErrorMessageText().contains("Epic sadface: Username and password do not match any user in this service"));
        softAssert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
                "User should remain on login page after invalid login");

        softAssert.assertAll();
    }
}
