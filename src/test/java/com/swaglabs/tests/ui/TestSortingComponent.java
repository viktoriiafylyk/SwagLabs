package com.swaglabs.tests.ui;

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
        inventoryPage.chooseAnOption("Name (Z to A)");
        softAssert.assertTrue(inventoryPage.isSortedAlphabeticallyDescending(), "Name Z to A failed");

        inventoryPage.chooseAnOption("Price (low to high)");
        softAssert.assertTrue(inventoryPage.isSortedByPriceAscending(), "Price low to high failed");

        inventoryPage.chooseAnOption("Price (high to low)");
        softAssert.assertTrue(inventoryPage.isSortedByPriceDescending(), "Price high to low failed");

        inventoryPage.chooseAnOption("Name (A to Z)");
        softAssert.assertTrue(inventoryPage.isSortedAlphabeticallyAscending(), "Name A to Z failed");

        softAssert.assertAll();
    }
}
