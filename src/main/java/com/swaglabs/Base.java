package com.swaglabs;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

public abstract class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor threadJs;
    protected Actions actions;

    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.threadJs = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("Scroll to the element")
    public void scrollToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    @Step("Scroll to the element")
    public void scrollToElementJs(WebElement element) {
        waitUntilElementVisible(element);
        try {
            threadJs.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        } catch (Exception e) {
            throw e;
        }
        waitUntilElementClickable(element);
    }

    @Step("Scroll to the middle of the page")
    public void scrollToMiddlePage() {
        Number startY = (Number) threadJs.executeScript("return window.pageYOffset;");
        threadJs.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");

        wait.until(driver -> {
            Number currentY = (Number) threadJs.executeScript("return window.pageYOffset;");
            return currentY.doubleValue() != startY.doubleValue();
        });
    }

    @Step("Scroll to the end of the page")
    public void scrollToEndOfPage() {
        sleep(1000);
        try {
            threadJs.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
        } catch (Exception e) {

            throw e;
        }
    }

    @Step("Check if content is truncated or overflows")
    protected boolean isContentTruncatedOrOverflow(WebElement element) {
        String script = "var element = arguments[0];" + "var computedStyle = window.getComputedStyle(element);" + "var isOverflowing = element.scrollHeight > element.clientHeight || element.scrollWidth > element.clientWidth;" + "var isTextOverflowing = computedStyle.overflow === 'hidden' || computedStyle.textOverflow === 'ellipsis' || computedStyle.whiteSpace === 'nowrap';" + "return isOverflowing && !isTextOverflowing;";

        Boolean isOverflowing;

        try {
            isOverflowing = (Boolean) threadJs.executeScript(script, element);
        } catch (Exception ex) {

            throw ex;
        }
        return isOverflowing != null && isOverflowing;
    }

    @Step("Click on the element")
    protected void clickDynamicElement(WebElement element) {
        waitUntilElementVisible(element);
        try {
            threadJs.executeScript("arguments[0].click();", element);
        } catch (Exception e) {

            throw e;
        }
    }

    public void updateElements(){
        PageFactory.initElements(this.driver, this);
    }

    public void sleep(long millisSeconds) {
        try {
            Thread.sleep(millisSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitCssValueChanged(Supplier<String> currentValueSupplier,
                                       String oldValue,
                                       Duration timeout) {

        new WebDriverWait(driver, timeout)
                .until(d -> !currentValueSupplier.get().equals(oldValue));
    }

    protected void waitUntilElementInvisible(WebElement element, Duration timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void waitUntilAllElementsVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitUntilPageLouder() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    @Step("Move to the element")
    protected void moveToElement(WebElement element) {
        waitUntilElementVisible(element);
        try {
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            throw new RuntimeException("Failed to move to the element: " + element, e);
        }
    }
}
