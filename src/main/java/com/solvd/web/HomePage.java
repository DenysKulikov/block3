package com.solvd.web;

import com.solvd.web.components.Header;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    @FindBy(xpath = "//*[@class = 'ui-library-stickyContainer-1244']")
    private Header header;
    @FindBy(xpath = "//a[contains(@href, '/uk/node/c1285101/')]")
    private ExtendedWebElement gameZoneButton;

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

    public ExtendedWebElement getGameZoneButton() {
        return gameZoneButton;
    }

    public GameZonePage clickGameZoneButton() {
        gameZoneButton.click();
        return new GameZonePage(getDriver());
    }
}
