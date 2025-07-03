package com.swaglabs.tests.dataprovider;

import org.testng.annotations.DataProvider;

public class InvalidLoginDataProvider {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"wrong_user", "secret_sauce"},
                {"standard_user", "wrong_password"},
                {" ", " "},
                {"standard_user", " "},
                {" ", "secret_sauce"}
        };

    }
}
