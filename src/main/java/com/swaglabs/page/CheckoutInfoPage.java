package com.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage {
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.cssSelector("#continue");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public void fillFormAndSubmit(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);

        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);

        driver.findElement(postalCodeInput).clear();
        driver.findElement(postalCodeInput).sendKeys(postalCode);

    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText().trim();
    }

    public void clickContinue (){
        driver.findElement(continueButton).click();
    }
}
