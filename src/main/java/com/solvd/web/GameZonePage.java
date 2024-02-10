package com.solvd.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class GameZonePage extends AbstractPage {
    @FindBy(xpath = "//a[@href = '/uk/games/c1038940/' and contains(@class, 'SubCategorystyled__ImageAction')]")
    private ExtendedWebElement gamesImage;

    @FindBy(css = "h1.ui-library-heading5-e356")
    private ExtendedWebElement title;

    public GameZonePage(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getGamesImage() {
        return gamesImage;
    }

    public ExtendedWebElement getTitleElement() {
        return title;
    }

    public GamesPage clickGamesImage() {
        gamesImage.click();
        return new GamesPage(getDriver());
    }
}
