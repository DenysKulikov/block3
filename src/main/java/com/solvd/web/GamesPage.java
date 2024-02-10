package com.solvd.web;

import com.solvd.web.components.ProductCard;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GamesPage extends AbstractPage {
    @FindBy(xpath = "//*[contains(@class, 'OfferTilestyled__StyledArticle')]")
    private List<ProductCard> productCards;

    @FindBy(css = "h1.ui-library-heading4-c1b7")
    private ExtendedWebElement title;

    public GamesPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    public ExtendedWebElement getTitleElement() {
        return title;
    }
}
