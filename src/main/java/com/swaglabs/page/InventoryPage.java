package com.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage extends BasePage {

    private final By googleDialogOkButton = By.xpath("//button[contains(text(), 'OK')");
    private final By inventoryContainer = By.id("inventory_container");
    private final By productItems = By.className("inventory_item");
    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("/inventory.html")
                && driver.findElement(inventoryContainer).isDisplayed();
    }

    public boolean areProductsDisplayed() {
        return !driver.findElements(productItems).isEmpty();
    }
}
