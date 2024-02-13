package com.solvd.web;

import com.solvd.web.components.Header;
import com.solvd.web.components.PotentialGift;
import com.solvd.web.components.ProductCard;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends AbstractPage {
    @FindBy(xpath = "//*[@class = 'ui-library-stickyContainer-1244']")
    private Header header;

    @FindBy(xpath = "//*[p[text()='Ігрова зона']]")
    private ExtendedWebElement gameZoneButton;

    @FindBy(xpath = "//*[span[text()=\"Ідеї для подарунків\"]]")
    private ExtendedWebElement giftIdeasButton;

    @FindBy(xpath = "//input[@type = 'number'][1]")
    private ExtendedWebElement giftPriceRangeStart;

    @FindBy(xpath = "//input[@type = 'number'][2]")
    private ExtendedWebElement giftPriceRangeEnd;

    @FindBy(xpath = "//button[@class= 'submit-prices']")
    private ExtendedWebElement generatePotentialGiftsButton;

    @FindBy(xpath = "//button[text() = 'Згенерувати ще']")
    private ExtendedWebElement generateMorePotentialGiftsButton;

    @FindBy(xpath = "//div[@class = 'goods-item-content']")
    private List<PotentialGift> potentialGifts;

    @FindBy(xpath = "//*[contains(@class, 'OfferTilestyled__StyledArticle')]")
    private List<ProductCard> productCards;

    @FindBy(xpath = "//*[contains(@class, 'ui-library-body4Regular-df09 ui-library-badge-a538')]")
    private ExtendedWebElement numberAddedToCartProducts;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        openURL(Configuration.getRequired("home_url"));
    }

    public Header getHeader() {
        return header;
    }

    public boolean isGameZoneButtonPresent() {
        return gameZoneButton.isElementPresent();
    }

    public GameZonePage clickGameZoneButton() {
        gameZoneButton.click();
        return new GameZonePage(getDriver());
    }

    public void clickGiftIdeasButton() {
        giftIdeasButton.click();
    }

    public double getGiftPriceRangeStart() {
        return Double.parseDouble(giftPriceRangeStart.getAttribute("value"));
    }

    public boolean isGiftPriceRangeStartIsPresent() {
        return waitUntil(input -> giftPriceRangeStart.isElementPresent(), 10);
    }

    public double getGiftPriceRangeEnd() {
        return Double.parseDouble(giftPriceRangeEnd.getAttribute("value"));
    }

    public boolean isGiftPriceRangeEndIsPresent() {
        return waitUntil(input -> giftPriceRangeEnd.isElementPresent(), 10);
    }

    public void clickGeneratePotentialGiftsButton() {
        generatePotentialGiftsButton.click();
    }

    public boolean isPotentialGiftsIsPresent() {
        return waitUntil(value -> !potentialGifts.isEmpty(), 10);
    }

    public List<PotentialGift> getPotentialGifts() {
        return potentialGifts;
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    public boolean isNumberAddedToCartProductsNotPresent() {
        return waitUntil(input -> numberAddedToCartProducts.isElementNotPresent(2), 10);
    }
}
