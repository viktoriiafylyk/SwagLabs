package com.swaglabs.tests.ui;

import com.swaglabs.component.HeaderComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAddItemsToCart extends BaseTest {

    @Test()
    @Description("Verify that adding products to cart and removing them updates the cart counter correctly.")
    @Feature("Adding and removing items updates counter")
    @Issue("5")
    @Owner("Fylyk Viktoriia")

    public void testAddItemsToCart() {
        HeaderComponent headerComponent = new HeaderComponent(driver, inventoryPage.getHeaderRoot());
        inventoryPage.getAllInventoryItems();

        inventoryPage.addProductsToCart(1);
        Assert.assertEquals(headerComponent.getCartCounter(), 1, "Cart counter should be 1 after 1 item");

        inventoryPage.addProductsToCart(1);
        Assert.assertEquals(headerComponent.getCartCounter(), 2, "Cart counter should be 2 after 2 items");

        inventoryPage.addProductsToCart(1);
        Assert.assertEquals(headerComponent.getCartCounter(), 3, "Cart counter should be 3 after 3 items");

        inventoryPage.addProductsToCart(1);
        Assert.assertEquals(headerComponent.getCartCounter(), 4, "Cart counter should be 4 after 4 items");

        inventoryPage.clickRemoveFromCart(1);
        Assert.assertEquals(headerComponent.getCartCounter(), 3, "Cart counter should be 3 after 1 removal");

        inventoryPage.clickRemoveFromCart(1);
        Assert.assertEquals(headerComponent.getCartCounter(), 2, "Cart counter should be 2 after 2 removals");

        inventoryPage.clickRemoveFromCart(1);
        Assert.assertEquals(headerComponent.getCartCounter(), 1, "Cart counter should be 1 after 3 removals");
    }
}
