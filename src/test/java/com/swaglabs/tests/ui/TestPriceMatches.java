package com.swaglabs.tests.ui;

import com.swaglabs.component.InventoryItemComponent;
import com.swaglabs.page.InventoryItemPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestPriceMatches extends BaseTest {

    @Test
    @Description("Verify that price on Inventory page matches the price on Inventory Item page.")
    @Feature("price matching verification")
    @Issue("7")
    @Owner("Fylyk Viktoriia")
    public void testPriceMatches() {
        List<InventoryItemComponent> items = inventoryPage.getAllInventoryItems();
        for (int i = 0; i < items.size(); i++) {
            InventoryItemComponent item = items.get(i);

            String mainPagePrice = item.getPrice();
            String itemName = item.getName();

            item.clickTitle();

            InventoryItemPage itemPage = new InventoryItemPage(driver);
            String detailsPagePrice = itemPage.getPrice();

            Assert.assertEquals(detailsPagePrice, mainPagePrice,
                    "Price mismatch for product: " + itemName);

            driver.navigate().back();
            items = inventoryPage.getAllInventoryItems();
        }
    }
}
