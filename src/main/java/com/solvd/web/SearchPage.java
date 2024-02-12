package com.solvd.web;

import com.solvd.web.components.Header;
import com.solvd.web.components.ProductCard;
import com.solvd.web.components.cart.PopupWindow;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends AbstractPage {
    @FindBy(xpath = "//*[@class = 'ui-library-stickyContainer-1244']")
    private Header header;

    @FindBy(css = "h5.ui-library-heading4-c1b7")
    private ExtendedWebElement title;

    @FindBy(xpath = "//*[contains(@class, 'OfferTilestyled__StyledArticle')]")
    private List<ProductCard> productCards;

    @FindBy(xpath = ".//*[@class = 'basket-preview-container']")
    private PopupWindow popupWindow;

    @FindBy(css = "a.ui-library-buttonSuccess-aaf1")
    private ExtendedWebElement successAddedButton;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public Header getHeader() {
        return header;
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    public ExtendedWebElement getTitleElement() {
        return title;
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public ExtendedWebElement getSuccessAddedButton() {
        return successAddedButton;
    }
}
