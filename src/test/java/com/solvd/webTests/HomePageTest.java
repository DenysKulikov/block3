package com.solvd.webTests;

import com.solvd.web.*;
import com.solvd.web.components.Header;
import com.solvd.web.components.ProductCard;
import com.solvd.web.components.cart.CartProduct;
import com.solvd.web.components.cart.PopupWindow;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class HomePageTest extends AbstractTest {
    @DataProvider
    public Object[][] brandNames() {
        return new Object[][] {
                {"iPhone"}
        };
    }

    @DataProvider
    public Object[][] gameNames() {
        return new Object[][] {
                {"Cyberpunk 2077"}
        };
    }

    @Test
    public void verifyIfTextPresenceTest() {
        String brandName = "iPhone";

        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();

        Assert.assertTrue(header.getSearchInput().isElementPresent(),
                "Search input is not present");

        Assert.assertEquals(homePage.getHeader().getSearchInputPlaceholder(), "Пошук товарів",
                "Search input has an incorrect placeholder");

        header.typeSearchInputValue(brandName);

        SearchPage searchPage = header.clickEnter();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchPage.getTitleElement()));

        String actualText = searchPage.getTitleElement().getText();
        Assert.assertTrue(actualText.toLowerCase().contains(brandName.toLowerCase()),
                "Expected text not found in title element");

        Assert.assertTrue(driver.getCurrentUrl().contains(brandName),
                "Url doesn't contain the brand name");
    }

    @Test
    public void verifyIdeasForGiftsTest() {
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Assert.assertTrue(homePage.getHeader().getSearchInput().isElementPresent(),
                "Search input is not present");

        homePage.clickGiftIdeasButton();

        boolean isGiftPriceRangeStartVisible = homePage.isGameZoneButtonPresent();

        Assert.assertTrue(isGiftPriceRangeStartVisible, "Gift price range start is not visible");

    }

    @Test(dataProvider = "gameNames")
    public void verifyGamePresenceTest(String gameName) {
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Assert.assertTrue(homePage.getHeader().getSearchInput().isElementPresent(),
                "Search input is not present");

        Assert.assertTrue(homePage.isGameZoneButtonPresent(),
                "Game zone button is not present");

        GameZonePage gameZonePage = homePage.clickGameZoneButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(gameZonePage.getTitleElement()));

        GamesPage gamesPage = gameZonePage.clickGamesButton();

        wait.until(ExpectedConditions.visibilityOf(gamesPage.getTitleElement()));

        List<ProductCard> productCards = gamesPage.getProductCards();

        boolean gameFound = productCards.stream()
                .anyMatch(cartProduct -> cartProduct.getTitleElement().getText().contains(gameName));

        Assert.assertTrue(gameFound, "Page doesn't contains provided game");
    }

    @Test(dataProvider = "brandNames")
    public void verifyCartTotalAmountTest(String brandName) {
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();

        Assert.assertTrue(header.getSearchInput().isElementPresent(),
                "Search input is not present");

        header.typeSearchInputValue(brandName);

        SearchPage searchPage = header.clickEnter();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchPage.getTitleElement()));

        List<ProductCard> productCards = searchPage.getProductCards();
        ProductCard firstProductCard = productCards.get(0);
        ProductCard secondProductCard = productCards.get(1);

        String expectedFirstProductTitle = firstProductCard.getTitleText();
        String expectedSecondProductTitle = firstProductCard.getTitleText();

        Assert.assertTrue(firstProductCard.getButton().isElementPresent());
        firstProductCard.getButton().click();
        wait.until(ExpectedConditions.visibilityOf(searchPage.getSuccessAddedButton()));

        if (secondProductCard.getButton().isElementPresent()) {
            secondProductCard.getButton().click();
            PopupWindow popupWindow = searchPage.getPopupWindow();
            wait.until(ExpectedConditions.visibilityOf(popupWindow.getButton()));
        }

        PopupWindow popupWindow = searchPage.getPopupWindow();

        CartPage cartPage = new CartPage(driver);

        popupWindow.getButton().click();
        wait.until(ExpectedConditions.visibilityOf(cartPage.getTitleElement()));

        List<CartProduct> products = cartPage.getProducts();
        double productsTotalPrice = products.stream()
                .mapToDouble(product -> Double.parseDouble(product.getCurrentPrice().
                        getText().replace(" ", "")))
                .sum();

        double cartPageTotalPrice = Double.parseDouble(cartPage.getTotalPrice().
                getText().replace(" ", ""));

        Assert.assertEquals(productsTotalPrice, cartPageTotalPrice,
                "Products total price doesn't match cart page total price");

        boolean firstProductContainsBrandName = products.stream()
                .anyMatch(cartProduct -> cartProduct.getTitleElement().getText().equalsIgnoreCase(expectedFirstProductTitle));

        boolean secondProductContainsBrandName = products.stream()
                .anyMatch(cartProduct -> cartProduct.getTitleElement().getText().equalsIgnoreCase(expectedSecondProductTitle));

        Assert.assertTrue(firstProductContainsBrandName, "Added product doesn't contain expected title: " + expectedFirstProductTitle);
        Assert.assertTrue(secondProductContainsBrandName, "Added product doesn't contain expected title: " + expectedSecondProductTitle);
    }

    @Test(dataProvider = "brandNames")
    public void verifySearchLineTest(String brandName) {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();

        header.typeSearchInputValue(brandName);

        SearchPage searchPage = header.clickEnter();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchPage.getTitleElement()));

        List<ProductCard> productCards = searchPage.getProductCards();

        for (ProductCard card : productCards) {
            softAssert.assertTrue(card.getTitleText().toLowerCase().contains(brandName.toLowerCase()),
                    String.format("Product with name '%s' doesn't contain the brand name in its title",
                            card.getTitleText()));
        }

        softAssert.assertAll();
    }
}
