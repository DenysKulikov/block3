package com.solvd.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class GameZonePage extends AbstractPage {
    @FindBy(xpath = "//a[span[text()=\"Ігри\"]]")
    private ExtendedWebElement gamesButton;

    @FindBy(css = "h1.ui-library-heading5-e356")
    private ExtendedWebElement title;

    public GameZonePage(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getGamesButton() {
        return gamesButton;
    }

    public ExtendedWebElement getTitleElement() {
        return title;
    }

    public GamesPage clickGamesButton() {
        gamesButton.click();
        return new GamesPage(getDriver());
    }
}
