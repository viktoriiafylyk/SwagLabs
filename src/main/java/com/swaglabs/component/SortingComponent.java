package com.swaglabs.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SortingComponent extends BaseComponent {

    private final By dropdownSelect = By.className("product_sort_container");

    public SortingComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    private Select getSelect() {
        WebElement selectElement = rootElement.findElement(dropdownSelect);
        return new Select(selectElement);
    }

    public void sortByNameAsc() {
        getSelect().selectByVisibleText("Name (A to Z)");
    }

    public void sortByNameDesc() {
        getSelect().selectByVisibleText("Name (Z to A)");
    }

    public void sortByPriceLowToHigh() {
        getSelect().selectByVisibleText("Price (low to high)");
    }

    public void sortByPriceHighToLow() {
        getSelect().selectByVisibleText("Price (high to low)");
    }

}
