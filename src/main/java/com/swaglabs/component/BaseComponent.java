package com.swaglabs.component;

import com.swaglabs.Base;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class BaseComponent extends Base {

    @Getter
    protected WebElement rootElement;

    public BaseComponent(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.rootElement = rootElement;
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);

    }
}
