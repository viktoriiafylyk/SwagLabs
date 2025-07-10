package com.swaglabs.tests.ui;

import com.swaglabs.component.SortingComponent;
import com.swaglabs.tests.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestSortingComponent extends BaseTestRunner {
    @Test()
    @Description("Verify that the drop-down menu sorts correctly by price and alphabet.")
    @Feature("Sorting component(drop-down menu) verification")
    @Issue("3")
    @Owner("Fylyk Viktoriia")

    public void testProductSorting() {
        SoftAssert softAssert = new SoftAssert();
        inventoryPage.clickOnSortComponent();
        SortingComponent sorting = inventoryPage.getSortingComponent();

        sorting.sortByNameDesc();
        softAssert.assertTrue(inventoryPage.isSortedAlphabeticallyDescending(), "Name Z to A failed");

        sorting.sortByPriceLowToHigh();
        softAssert.assertTrue(inventoryPage.isSortedByPriceAscending(), "Price low to high failed");

        sorting.sortByPriceHighToLow();
        softAssert.assertTrue(inventoryPage.isSortedByPriceDescending(), "Price high to low failed");

        sorting.sortByNameAsc();
        softAssert.assertTrue(inventoryPage.isSortedAlphabeticallyAscending(), "Name A to Z failed");

        softAssert.assertAll();
    }
}
