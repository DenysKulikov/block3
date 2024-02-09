package com.solvd.web;

import com.solvd.web.components.ProductCard;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends AbstractPage {
    @FindBy(xpath = ".//*[contains(@class, 'OfferTilestyled__StyledArticle')]")
    private List<ProductCard> productCards;
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }
}
