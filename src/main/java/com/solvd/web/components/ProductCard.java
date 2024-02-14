package com.solvd.web.components;


import com.solvd.web.components.cart.PopupCartWindow;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductCard extends AbstractUIObject {
    @FindBy(xpath = ".//*[contains(@class, 'GoodsDescriptionstyled__StyledLinkWrapper')]")
    private ExtendedWebElement titleElement;

    @FindBy(xpath = ".//button[contains(@class, 'ui-library-buttonPrimary')]")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = ".//span[@class = 'ui-library-subtitle1Bold-399e']")
    private ExtendedWebElement productPrice;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getTitleText() {
        return titleElement.getText();
    }

    public PopupCartWindow addToCartButton() {
        addToCartButton.click();
        return new PopupCartWindow(getDriver());
    }

    public boolean isAddToCartButtonIsPresent() {
        return addToCartButton.isElementPresent(5);
    }

    public double getProductPrice() {
        return Double.parseDouble(productPrice.getText().replace(" ", ""));
    }

    public void hoverOnTitle() {
        titleElement.hover();
    }
}
