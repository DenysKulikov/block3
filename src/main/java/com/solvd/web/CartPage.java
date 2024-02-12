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

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartProduct> getProducts() {
        return products;
    }

    public boolean isProductsIsPresent() {
        return waitUntil(value -> !products.isEmpty(), 10);
    }
}
