package com.swaglabs.tests.ui;

import com.swaglabs.component.HeaderComponent;
import com.swaglabs.page.CartPage;
import com.swaglabs.page.CheckoutInfoPage;
import com.swaglabs.page.CheckoutOverviewPage;
import com.swaglabs.tests.dataprovider.ProductCountDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestTaxIsEightPercent extends BaseTest {
    @Test(dataProvider = "productCountData", dataProviderClass = ProductCountDataProvider.class)
    @Description("Verify that the tax on the checkout page is always 8% of the Item total.")
    @Feature("Tax calculation")
    @Issue("8")
    @Owner("Fylyk Viktoriia")
    public void testTaxIs8Percent(int numberOfProducts) {
        SoftAssert softAssert = new SoftAssert();
        HeaderComponent headerComponent = new HeaderComponent(driver, inventoryPage.getHeaderRoot());

        inventoryPage.removeAllProductsFromCart();
        inventoryPage.addProductsToCart(numberOfProducts);
        softAssert.assertEquals(headerComponent.getCartCounter(), numberOfProducts,
                "Cart counter should show correct number of items.");

        headerComponent.clickCartButton();
        CartPage cartPage = new CartPage(driver);
        CheckoutInfoPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.fillFormAndSubmit("Viktoriia", "Fylyk", "123456");
        checkoutPage.clickContinue();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        double itemTotal = overviewPage.getItemTotal();
        double actualTax = overviewPage.getTax();

        double expectedTax = Math.round(itemTotal * 0.08 * 100.0) / 100.0;

        softAssert.assertEquals(actualTax, expectedTax,
                String.format("Expected tax to be 8%% of %.2f = %.2f, but was %.2f",
                        itemTotal, expectedTax, actualTax));

        softAssert.assertAll();
    }

}
