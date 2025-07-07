// TestInvalidCheckoutForm.java
package com.swaglabs.tests.ui;

import com.swaglabs.component.HeaderComponent;
import com.swaglabs.page.CartPage;
import com.swaglabs.page.CheckoutInfoPage;
import com.swaglabs.tests.dataprovider.EmptyCheckoutDataProvider;
import com.swaglabs.tests.ui.testrunners.BaseTestRunner;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestInvalidCheckoutForm extends BaseTestRunner {

    @Test(dataProvider = "checkoutData", dataProviderClass = EmptyCheckoutDataProvider.class)
    @Description("Verify the processing of empty data during checkout.")
    @Feature("Checkout form validation")
    @Issue("6")
    @Owner("Fylyk Viktoriia")
    public void testInvalidCheckoutForm(String firstName, String lastName, String postalCode) {
        inventoryPage.addProductsToCart(1);
        HeaderComponent headerComponent = new HeaderComponent(driver, inventoryPage.getHeaderRoot());
        headerComponent.clickCartButton();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isOnCartPage(), "Not on Cart page!");
        CheckoutInfoPage checkoutPage = cartPage.clickCheckoutButton();

        checkoutPage.fillFormAndSubmit(firstName.trim(), lastName.trim(), postalCode.trim());
        String error = checkoutPage.getErrorMessage();

        if (firstName.trim().isEmpty()) {
            Assert.assertEquals(error, "Error: First Name is required");
        } else if (lastName.trim().isEmpty()) {
            Assert.assertEquals(error, "Error: Last Name is required");
        } else if (postalCode.trim().isEmpty()) {
            Assert.assertEquals(error, "Error: Postal Code is required");
        }
    }
}
