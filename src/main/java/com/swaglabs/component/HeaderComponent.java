package com.swaglabs.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HeaderComponent extends BaseComponent {

    private final By burgerButton = By.xpath("//*[@id='react-burger-menu-btn']");

    private final By cartButton = By.cssSelector(".shopping_cart_link");

    private final By cartCounter = By.xpath("//span[@class='shopping_cart_badge']");

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickBurgerButton() {
        rootElement.findElement(burgerButton).click();
    }

    public void clickCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartBtn.click();
    }

    public int getCartCounter() {
        List<WebElement> counters = driver.findElements(cartCounter);
        return counters.isEmpty() ? 0 : Integer.parseInt(counters.get(0).getText());
    }
}
