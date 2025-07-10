package com.swaglabs.tests.dataprovider;

import org.testng.annotations.DataProvider;

public class ProductCountDataProvider {
    @DataProvider(name = "productCountData")
    public Object[][] productCounts() {
        return new Object[][]{
                {1},
                {2},
                {3},
                {4},
                {5},
                {6}
        };
    }
}
