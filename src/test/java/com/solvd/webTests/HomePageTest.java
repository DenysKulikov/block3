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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class HomePageTest extends AbstractTest {
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
    public void verifyGamePresenceTest() {
        String gameName = "Cyberpunk 2077";

        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Assert.assertTrue(homePage.getGameZoneButton().isElementPresent(),
                "Game zone button is not present");

        GameZonePage gameZonePage = homePage.clickGameZoneButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(gameZonePage.getTitleElement()));

        GamesPage gamesPage = gameZonePage.clickGamesImage();

        wait.until(ExpectedConditions.visibilityOf(gamesPage.getTitleElement()));

        List<ProductCard> productCards = gamesPage.getProductCards();

        boolean gameFound = productCards.stream()
                .anyMatch(cartProduct -> cartProduct.getTitleElement().getText().contains(gameName));

        Assert.assertTrue(gameFound, "Page doesn't contains provided game");
    }

    @Test
    public void verifyCartTotalAmountTest() {
        String brandName = "iPhone";

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

        firstProductCard.getButton().click();
        wait.until(ExpectedConditions.visibilityOf(searchPage.getSuccessAddedButton()));

        secondProductCard.getButton().click();

        PopupWindow popupWindow = searchPage.getPopupWindow();
        wait.until(ExpectedConditions.visibilityOf(popupWindow.getButton()));

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
    }

    @Test
    public void verifySearchLineTest() {
        String brandName = "iPhone";

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

    @Test
    public void verifyCartTest() {
        String brandName = "iPhone";

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

        firstProductCard.getButton().click();

        PopupWindow popupWindow = searchPage.getPopupWindow();
        wait.until(ExpectedConditions.visibilityOf(popupWindow.getButton()));

        CartPage cartPage = new CartPage(driver);

        popupWindow.getButton().click();
        wait.until(ExpectedConditions.visibilityOf(cartPage.getTitleElement()));

        List<CartProduct> products = cartPage.getProducts();
        boolean firstProductContainsBrandName = products.stream()
                .findFirst()
                .map(cartProduct -> cartProduct.getTitleElement().getText().toLowerCase().contains(brandName.toLowerCase()))
                .orElse(false);

        Assert.assertTrue(firstProductContainsBrandName, "Added product doesn't contains brand name");
    }
}
