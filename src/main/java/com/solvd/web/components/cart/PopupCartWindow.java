package com.solvd.web.components.cart;

import com.solvd.web.CartPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PopupCartWindow extends AbstractUIObject {
    @FindBy(xpath = ".//*[@class = 'basket-button']")
    private ExtendedWebElement buttonToCartPage;

    public PopupCartWindow(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public PopupCartWindow(WebDriver driver) {
        super(driver);
    }

    public CartPage clickButtonToCartPage() {
        buttonToCartPage.click();
        return new CartPage(getDriver());
    }

    public boolean isButtonToCartPresent() {
        return buttonToCartPage.isElementPresent(5);
    }
}
