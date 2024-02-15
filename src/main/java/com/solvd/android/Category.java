package com.solvd.android;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Category extends AbstractUIObject {
    @FindBy(xpath = ".//*[contains(@resource-id, 'id/text')]")
    private ExtendedWebElement categoryTitle;

    @FindBy(xpath = ".//android.widget.ImageView[contains(@resource-id, 'id/more')]")
    private ExtendedWebElement categoryProperties;

    public Category(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getCategoryText() {
        return categoryTitle.getText();
    }

    public void clickCategoryProperties() {
        categoryProperties.click();
    }
}
