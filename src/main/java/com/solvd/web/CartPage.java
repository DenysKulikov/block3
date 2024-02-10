package com.solvd.web;

import com.solvd.web.components.cart.CartProduct;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {
    @FindBy(css = "h5.cart-header")
    private ExtendedWebElement title;
    @FindBy(xpath = "//*[@class= 'goods-tile-container']")
    private List<CartProduct> products;

    @FindBy(css = "div.total-number span.value")
    private ExtendedWebElement totalPrice;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getTitleElement() {
        return title;
    }

    public List<CartProduct> getProducts() {
        return products;
    }

    public ExtendedWebElement getTotalPrice() {
        return totalPrice;
    }
}
