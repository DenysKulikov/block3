package com.solvd.web;

import com.solvd.web.components.Header;
import com.solvd.web.components.ProductCard;
import com.solvd.web.components.cart.PopupCartWindow;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'stickyContainer')]")
    private Header header;

    @FindBy(css = "div>h5")
    private ExtendedWebElement title;

    @FindBy(css = "div>article>div")
    private List<ProductCard> productCards;

    @FindBy(xpath = ".//*[@class = 'basket-preview-container']")
    private PopupCartWindow popupWindow;

    @FindBy(css = "a.ui-library-buttonSuccess-aaf1")
    private ExtendedWebElement successAddedButton;

    @FindBy(xpath = "//ul")
    private ExtendedWebElement paginationElement;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public Header getHeader() {
        return header;
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    public PopupCartWindow getPopupWindow() {
        return popupWindow;
    }

    public boolean isTitleElementIsPresent() {
        return title.isElementPresent(5);
    }

    public boolean isSuccessAddedButtonIsPresent() {
        return successAddedButton.isElementPresent(5);
    }

    public String getTitleText() {
        return title.getText();
    }

    public boolean areProductsCardsPresent() {
        return waitUntil(value -> !productCards.isEmpty(), 10);
    }

    public boolean isPaginationPresent() {
        return paginationElement.isElementPresent(5);
    }
}
