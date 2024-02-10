package com.solvd.web.components;

import com.solvd.web.SearchPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractUIObject {
    @FindBy(xpath = ".//*[@type= 'search']")
    private ExtendedWebElement searchInput;

    public Header(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getSearchInput() {
        return searchInput;
    }

    public String getSearchInputPlaceholder() {
        return searchInput.getAttribute("placeholder");
    }

    public void typeSearchInputValue(String value) {
        searchInput.type(value);
    }

    public SearchPage clickEnter() {
        searchInput.sendKeys(Keys.ENTER);
        return new SearchPage(getDriver());
    }
}
