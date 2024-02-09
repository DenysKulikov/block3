package com.solvd.webTests;

import com.solvd.web.HomePage;
import com.solvd.web.SearchPage;
import com.solvd.web.components.Header;
import com.solvd.web.components.ProductCard;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HomePageTest extends AbstractTest {
    @Test
    public void verifySearchLineTest() {
        String brandName = "iPhone";

        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();

        Assert.assertTrue(header.getSearchInput().isElementPresent(1),
                "Search input is not present");
        softAssert.assertEquals(homePage.getHeader().getSearchInputPlaceholder(), "Пошук товарів",
                "Search input has an incorrect placeholder");

        header.typeSearchInputValue(brandName);

        SearchPage searchPage = header.clickEnter();

        // Now the SearchPage is likely loaded, continue with your assertions:
        Assert.assertTrue(driver.getCurrentUrl().contains(brandName),
                "Url doesn't contain the brand name");

        pause(3);
        List<ProductCard> productCards = searchPage.getProductCards();

        for (ProductCard card : productCards) {
            softAssert.assertTrue(card.getTitleText().toLowerCase().contains(brandName.toLowerCase()),
                    String.format("Product with name '%s' doesn't contain the brand name in its title",
                            card.getTitleText()));
        }

        softAssert.assertAll();
    }
}
