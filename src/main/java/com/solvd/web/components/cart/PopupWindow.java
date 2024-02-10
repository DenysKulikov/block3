package com.solvd.web.components.cart;

import com.solvd.web.CartPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PopupWindow extends AbstractUIObject {
    @FindBy(xpath = ".//*[@class = 'basket-button']")
    private ExtendedWebElement buttonToCartPage;

    public PopupWindow(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getButton() {
        return buttonToCartPage;
    }

    public CartPage clickButtonToCartPage() {
        buttonToCartPage.click();
        return new CartPage(getDriver());
    }
}
