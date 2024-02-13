package com.solvd.webTests;

import com.solvd.web.*;
import com.solvd.web.components.Header;
import com.solvd.web.components.PotentialGift;
import com.solvd.web.components.ProductCard;
import com.solvd.web.components.cart.CartProduct;
import com.solvd.web.components.cart.PopupWindow;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    public void verifyIfProductDeletesFromCartTest() {
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Assert.assertTrue(homePage.getHeader().isSearchInputIsPresent(),
                "Search input is not present");

        List<ProductCard> productCards = homePage.getProductCards();
        ProductCard productCard = productCards.get(0);
        productCard.hoverOnTitle();

        Assert.assertTrue(productCard.isAddToCartButtonIsPresent());

        PopupWindow popupWindow = productCard.addToCartButton();

        popupWindow.isButtonToCartPresent();

        CartPage cartPage = popupWindow.clickButtonToCartPage();

        cartPage.isProductsIsPresent();

        List<CartProduct> cartProducts = cartPage.getProducts();
        CartProduct cartProduct = cartProducts.get(0);
        cartProduct.clickButtonDeleteProduct();

        Assert.assertTrue(homePage.isNumberAddedToCartProductsNotPresent());
    }

    @Test
    public void verifyIdeasForGiftsTest() {
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Assert.assertTrue(homePage.getHeader().isSearchInputIsPresent(),
                "Search input is not present");

        homePage.clickGiftIdeasButton();

        boolean isGiftPriceRangeStartPresent = homePage.isGiftPriceRangeStartIsPresent();

        Assert.assertTrue(isGiftPriceRangeStartPresent, "Gift price range start is not present");

        boolean isGiftPriceRangeEndPresent = homePage.isGiftPriceRangeEndIsPresent();
        Assert.assertTrue(isGiftPriceRangeEndPresent, "Gift price range end is not present");

        double giftPriceRangeStart = homePage.getGiftPriceRangeStart();
        double giftPriceRangeEnd = homePage.getGiftPriceRangeEnd();

        homePage.clickGeneratePotentialGiftsButton();

        homePage.isPotentialGiftsIsPresent();

        List<PotentialGift> potentialGifts = homePage.getPotentialGifts();

        for (PotentialGift potentialGift : potentialGifts) {
            double potentialGiftPrice = potentialGift.getPotentialGiftPrice();
            Assert.assertTrue(potentialGiftPrice >= giftPriceRangeStart && potentialGiftPrice <= giftPriceRangeEnd,
                    "Potential gift price is not within the specified range");
        }
    }

    @Test(dataProvider = "gameNames")
    public void verifyGamePresenceTest(String gameName) {
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();

        Assert.assertTrue(homePage.getHeader().isSearchInputIsPresent(),
                "Search input is not present");

        Assert.assertEquals(header.getSearchInputPlaceholder(), "Пошук товарів",
                "Search input has an incorrect placeholder");

        Assert.assertTrue(homePage.isGameZoneButtonPresent(),
                "Game zone button is not present");

        GameZonePage gameZonePage = homePage.clickGameZoneButton();

        gameZonePage.isTitleElementIsPresent();

        GamesPage gamesPage = gameZonePage.clickGamesButton();

        gamesPage.isTitleElementIsPresent();

        List<ProductCard> productCards = gamesPage.getProductCards();

        boolean gameFound = productCards.stream()
                .anyMatch(cartProduct -> cartProduct.getTitleText().contains(gameName));

        Assert.assertTrue(gameFound, "Page doesn't contains provided game");
    }

    @Test(dataProvider = "brandNames")
    public void verifyCartTotalAmountTest(String brandName) {
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();

        Assert.assertTrue(header.isSearchInputIsPresent(),
                "Search input is not present");

        header.typeSearchInputValue(brandName);

        SearchPage searchPage = header.clickEnter();

        searchPage.isTitleElementIsPresent();

        List<ProductCard> productCards = searchPage.getProductCards();
        ProductCard firstProductCard = productCards.get(0);

        String expectedProductTitle = firstProductCard.getTitleText();
        double expectedProductPrice = firstProductCard.getProductPrice();

        Assert.assertTrue(firstProductCard.isAddToCartButtonIsPresent(), "Add to cart button is not present");
        PopupWindow popupWindow = firstProductCard.addToCartButton();
        searchPage.isSuccessAddedButtonIsPresent();

        popupWindow.isButtonToCartPresent();

        CartPage cartPage = popupWindow.clickButtonToCartPage();
        cartPage.isProductsIsPresent();

        List<CartProduct> cartProducts = cartPage.getProducts();
        double productsTotalPriceInCart = cartProducts.stream()
                .mapToDouble(CartProduct::getProductPrice)
                .sum();

        boolean cartProductContainsBrandName = cartProducts.stream()
                .anyMatch(cartProduct -> cartProduct.getTitleText().equalsIgnoreCase(expectedProductTitle));

        Assert.assertEquals(productsTotalPriceInCart, expectedProductPrice,
                "Price For the cart Product and expected product doesn't match");
        Assert.assertTrue(cartProductContainsBrandName, "Added product doesn't contain expected title: "
                + expectedProductTitle);
    }

    @Test(dataProvider = "brandNames")
    public void verifySearchLineTest(String brandName) {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();

        Assert.assertTrue(header.isSearchInputIsPresent(),
                "Search input is not present");

        header.typeSearchInputValue(brandName);

        SearchPage searchPage = header.clickEnter();

        Assert.assertTrue(searchPage.isTitleElementIsPresent());

        List<ProductCard> productCards = searchPage.getProductCards();

        for (ProductCard card : productCards) {
            softAssert.assertTrue(card.getTitleText().toLowerCase().contains(brandName.toLowerCase()),
                    String.format("Product with name '%s' doesn't contain the brand name in its title",
                            card.getTitleText()));
        }

        softAssert.assertAll();
    }
}
