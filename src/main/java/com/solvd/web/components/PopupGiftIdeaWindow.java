package com.solvd.web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PopupGiftIdeaWindow extends AbstractUIObject {
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

    public PopupGiftIdeaWindow(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public double getGiftPriceRangeStart() {
        return Double.parseDouble(giftPriceRangeStart.getAttribute("value"));
    }

    public boolean isGiftPriceRangeStartPresent() {
        return giftPriceRangeStart.isElementPresent(5);
    }

    public double getGiftPriceRangeEnd() {
        return Double.parseDouble(giftPriceRangeEnd.getAttribute("value"));
    }

    public boolean isGiftPriceRangeEndPresent() {
        return giftPriceRangeEnd.isElementPresent(5);
    }

    public void clickGeneratePotentialGiftsButton() {
        generatePotentialGiftsButton.click();
    }

    public boolean isPotentialGiftsPresent() {
        return waitUntil(value -> !potentialGifts.isEmpty(), 10);
    }

    public List<PotentialGift> getPotentialGifts() {
        return potentialGifts;
    }
}
