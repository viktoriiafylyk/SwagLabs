package com.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {
    private final By itemTotal = By.cssSelector(".summary_subtotal_label");
    private final By tax = By.cssSelector(".summary_tax_label");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public double getItemTotal() {
        String text = driver.findElement(itemTotal).getText(); // "Item total: $29.99"
        String value = text.replace("Item total: $", "").trim();
        return Double.parseDouble(value);
    }

    public double getTax() {
        String text = driver.findElement(tax).getText(); // "Tax: $2.40"
        String value = text.replace("Tax: $", "").trim();
        return Double.parseDouble(value);
    }
}
