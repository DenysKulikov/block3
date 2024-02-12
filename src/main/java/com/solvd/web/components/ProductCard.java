package com.solvd.web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductCard extends AbstractUIObject {
    @FindBy(xpath = ".//*[contains(@class, 'GoodsDescriptionstyled__StyledLinkWrapper')]")
    private ExtendedWebElement titleElement;
    @FindBy(xpath = ".//button[contains(@class, 'ui-library-buttonPrimary')]")
    private ExtendedWebElement button;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getTitleElement() {
        return titleElement;
    }

    public String getTitleText() {
        return titleElement.getText();
    }

    public ExtendedWebElement getButton() {
        return button;
    }
}
