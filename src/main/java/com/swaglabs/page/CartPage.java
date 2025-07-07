package com.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
    private final By checkoutButton = By.xpath("//button[@id='checkout']");
    private final By continueShoppingButton = By.xpath("//button[@id='continue-shopping']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutInfoPage clickCheckoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutBtn.click();
        return new CheckoutInfoPage(driver);
    }


    public InventoryPage clickContinueShoppingButton() {
        driver.findElement(continueShoppingButton).click();
        return new InventoryPage(driver);
    }

    public boolean isOnCartPage() {
        return driver.getCurrentUrl().contains("cart.html");
    }

}

