package com.swaglabs.tests.ui;
import com.swaglabs.page.InventoryPage;
import com.swaglabs.page.LoginPage;
import com.swaglabs.tests.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestValidLogin extends BaseTestRunner {
    @Override
    protected boolean shouldLoginBeforeTest() {
        return false;
    }

    @Test
    @Description("Verify login with valid data.")
    @Feature("Login with valid data")
    @Issue("1")
    @Owner("Fylyk Viktoriia")
    public void testSuccessfulLogin() {
        String validUsername = testValueProvider.getUserEmail();
        String validPassword = testValueProvider.getUserName();

        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.login(validUsername, validPassword);

        assertTrue(inventoryPage.isOnInventoryPage(),
                "The inventory page should open after logging in");
        assertTrue(inventoryPage.areProductsDisplayed(),
                "Products should be displayed on inventory page");
    }
}
