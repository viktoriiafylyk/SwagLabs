package com.swaglabs.page;

import com.swaglabs.component.InventoryItemComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventoryPage extends BasePage {
    private final By inventoryContainer = By.id("inventory_container");
    private final By productItems = By.className("inventory_item");
    private final By sortContainer = By.className("product_sort_container");

    private final By removeFromCartButton = By.cssSelector("button[data-test^='remove']");

    private final By productName = By.className("inventory_item_name");
    private final By productPrice = By.className("inventory_item_price");

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

    public void clickOnSortComponent() {
        driver.findElement(sortContainer).click();
    }

    public void chooseAnOption(String visibleText) {
        WebElement dropdown = driver.findElement(sortContainer);
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        options.stream()
                .filter(option -> option.getText().equals(visibleText))
                .findFirst()
                .ifPresent(WebElement::click);
    }


    public List<String> getProductNames() {
        List<WebElement> nameElements = driver.findElements(productName);
        List<String> names = new ArrayList<>();
        for (WebElement element : nameElements) {
            names.add(element.getText().trim());
        }
        return names;
    }

    public List<Double> getProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPrice);
        List<Double> prices = new ArrayList<>();
        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public boolean isSortedAlphabeticallyAscending() {
        List<String> names = getProductNames();
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(String::compareToIgnoreCase);
        return names.equals(sorted);
    }

    public boolean isSortedAlphabeticallyDescending() {
        List<String> names = getProductNames();
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Comparator.reverseOrder());
        return names.equals(sorted);
    }

    public boolean isSortedByPriceAscending() {
        List<Double> prices = getProductPrices();
        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Double::compareTo);
        return prices.equals(sorted);
    }

    public boolean isSortedByPriceDescending() {
        List<Double> prices = getProductPrices();
        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Comparator.reverseOrder());
        return prices.equals(sorted);
    }

    public void addProductsToCart(int numberOfProducts) {
        List<WebElement> items = driver.findElements(By.className("inventory_item"));
        int count = 0;
        for (WebElement item : items) {
            if (count >= numberOfProducts) break;
            WebElement addButton = item.findElement(By.tagName("button"));
            if (addButton.getText().equals("Add to cart")) {
                addButton.click();
                new WebDriverWait(driver, Duration.ofSeconds(0, 5));
                count++;
            }
        }
    }

    public List<InventoryItemComponent> getAllInventoryItems() {
        List<WebElement> items = driver.findElements(By.className("inventory_item"));
        List<InventoryItemComponent> components = new ArrayList<>();
        for (WebElement item : items) {
            components.add(new InventoryItemComponent(driver, item));
        }
        return components;
    }

    public void removeAllProductsFromCart() {
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("button[data-test^='remove']"));
        for (WebElement btn : removeButtons) {
            btn.click();
        }
    }

    public void clickRemoveFromCart(int numberOfProducts) {
        driver.findElement(removeFromCartButton).click();
    }
}
