package com.solvd.web.components.cart;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartProduct extends AbstractUIObject {
    @FindBy(xpath = ".//a[@target = '_blank' and not(contains(@class, 'img-container'))]")
    private ExtendedWebElement titleElement;

    @FindBy(css = "div.current-price span.number")
    private ExtendedWebElement currentPrice;

    public CartProduct(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getTitleText() {
        return titleElement.getText();
    }

    public double getProductPrice() {
        return Double.parseDouble(currentPrice.getText().replace(" ", ""));
    }
}
