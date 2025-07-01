package com.swaglabs.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderComponent extends BaseComponent {

    private final By burgerButton = By.xpath("//*[@id='react-burger-menu-btn']");

    private final By cartButton = By.xpath("//a[@class='shopping_cart_link']");
    //*[@id="shopping_cart_container"]/a
    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickBurgerButton (){
        rootElement.findElement(burgerButton).click();
    }

    public void clickCartButton (){
        rootElement.findElement(cartButton).click();
    }

}
