package com.solvd.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CategoryPage extends AbstractPage {
    @FindBy(xpath = "//a[span[text()='%s']]")
    private ExtendedWebElement productsButton;

    @FindBy(xpath = "//h1[contains(@Class, 'CategoryTitle')]")
    private ExtendedWebElement title;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage clickGamesButton(String categoryName) {
        productsButton.format(categoryName).click();
        return new SearchPage(getDriver());
    }

    public boolean isTitleElementIsPresent() {
        return title.isElementPresent(5);
    }
}
