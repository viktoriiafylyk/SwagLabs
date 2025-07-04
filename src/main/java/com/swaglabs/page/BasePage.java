package com.swaglabs.page;

import com.swaglabs.Base;
import com.swaglabs.component.HeaderComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;


public abstract class BasePage extends Base {
    @Getter
    @FindBy(xpath = "//div[@id='app']/ul")
    private WebElement headerRoot;
    @Getter
    @FindBy(xpath = "//footer[@class='footer']")
    private WebElement footerRoot;
    private HeaderComponent header;
    private HeaderComponent footer;

    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.header = new HeaderComponent(driver, getHeaderRoot());
        this.footer = new HeaderComponent(driver, getFooterRoot());

    }

    @Step("Retrieve the full content height of the page")
    private int getContentHeight() {
        return ((Number) Objects.requireNonNull(threadJs.executeScript("return document.body.scrollHeight;"))).intValue();
    }

    @Step("Wait for the page to load within {0} seconds")
    public void waitForPageToLoad(long timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Check if element is invisible: {0}")
    public Boolean isElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    @Step("Scroll to footer")
    public void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}
