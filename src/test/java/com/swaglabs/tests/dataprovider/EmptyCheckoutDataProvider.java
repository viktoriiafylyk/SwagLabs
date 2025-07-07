package com.swaglabs.tests.dataprovider;

import org.testng.annotations.DataProvider;

public class EmptyCheckoutDataProvider {
    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return new Object[][]{
                {"Viktoriia", "Fylyk", " "},
                {"Viktoriia", " ", "123456"},
                {" ","Fylyk", "123456"},
        };
    }
}
