package com.solvd.web;

import com.solvd.web.components.Header;
import com.solvd.web.components.PopupGiftIdeaWindow;
import com.solvd.web.components.ProductCard;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends AbstractPage {
    @FindBy(xpath = "//*[@class = 'ui-library-stickyContainer-1244']")
    private Header header;

    @FindBy(xpath = "//p[text() = '%s']")
    private ExtendedWebElement categoryButton;

    @FindBy(xpath = "//*[span[text()=\"Ідеї для подарунків\"]]")
    private ExtendedWebElement giftIdeasButton;

    @FindBy(xpath = "//div[contains(@class, 'rodal-dialog')]")
    private PopupGiftIdeaWindow popupGiftIdeaWindow;

    @FindBy(css = "div>article>div")
    private List<ProductCard> productCards;

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

    public PopupGiftIdeaWindow clickGiftIdeasButton() {
        giftIdeasButton.click();
        return new PopupGiftIdeaWindow(driver);
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    public boolean isCategoryButtonPresent(String categoryName) {
        return categoryButton.format(categoryName).isElementPresent(5);
    }

    public CategoryPage clickCategoryButton(String categoryName) {
        categoryButton.format(categoryName).click();
        return new CategoryPage(getDriver());
    }
}
