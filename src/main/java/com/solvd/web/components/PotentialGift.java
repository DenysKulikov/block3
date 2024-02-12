package com.solvd.web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PotentialGift extends AbstractUIObject {
    @FindBy(xpath = ".//div[@class = 'price-wrapper']")
    private ExtendedWebElement potentialGift;

    public PotentialGift(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public double getPotentialGiftPrice() {
        WebElement priceSpan = potentialGift.findElement(By.tagName("span"));
        return Double.parseDouble(priceSpan.getText().replace(" ", ""));
    }
}
