package com.solvd.web;

import com.solvd.web.components.Header;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;

public class HomePage extends AbstractPage {
    @FindBy(xpath = "//*[@class = 'ui-library-stickyContainer-1244']")
    private Header header;
    @FindBy(xpath = "//*[p[text()='Ігрова зона']]")
    private ExtendedWebElement gameZoneButton;
    @FindBy(xpath = "//*[span[text()=\"Ідеї для подарунків\"]]")
    private ExtendedWebElement giftIdeasButton;
    @FindBy(xpath = "//input[@type = 'number'][1]")
    private ExtendedWebElement giftPriceRangeStart;

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
        gameZoneButton.click();
    }

    public double getGiftPriceRangeStart() {
        return Double.parseDouble(giftPriceRangeStart.getAttribute("value"));
    }

    public static boolean isGiftPriceRangeStartVisible(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@type = 'number'][1]"))));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
