package com.swaglabs.tests.ui;
import com.swaglabs.page.InventoryPage;
import com.swaglabs.page.LoginPage;
import com.swaglabs.tests.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestValidLogin extends BaseTestRunner {

    @Test
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
