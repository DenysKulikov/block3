package com.solvd.webTests;

import com.solvd.web.*;
import com.solvd.web.components.Header;
import com.solvd.web.components.PopupGiftIdeaWindow;
import com.solvd.web.components.PotentialGift;
import com.solvd.web.components.ProductCard;
import com.solvd.web.components.cart.CartProduct;
import com.solvd.web.components.cart.PopupCartWindow;
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

        Header header = homePage.getHeader();

        Assert.assertTrue(header.isSearchInputIsPresent(),
                "Search input is not present");

        List<ProductCard> productCards = homePage.getProductCards();
        ProductCard productCard = productCards.get(0);
        productCard.hoverOnTitle();

        Assert.assertTrue(productCard.isAddToCartButtonIsPresent(),
                "Add to cart button is not present");

        PopupCartWindow popupWindow = productCard.addToCartButton();

        Assert.assertTrue(popupWindow.isButtonToCartPresent(),
                "Button to cart is not present");

        CartPage cartPage = popupWindow.clickButtonToCartPage();

        Assert.assertTrue(cartPage.areProductsPresent(),
                "Products are not present");

        List<CartProduct> cartProducts = cartPage.getProducts();
        CartProduct cartProduct = cartProducts.get(0);
        cartProduct.clickButtonDeleteProduct();

        Assert.assertTrue(header.isNumberAddedToCartProductsNotPresent(),
                "Number added to cart products are not present");
    }

    @Test
    public void verifyIdeasForGiftsTest() {
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();

        Assert.assertTrue(header.isSearchInputIsPresent(),
                "Search input is not present");

        PopupGiftIdeaWindow popupGiftIdeaWindow = homePage.clickGiftIdeasButton();

        Assert.assertTrue(popupGiftIdeaWindow.isGiftPriceRangeStartIsPresent(),
                "Gift price range start is not present");

        Assert.assertTrue(popupGiftIdeaWindow.isGiftPriceRangeEndIsPresent(),
                "Gift price range end is not present");

        double giftPriceRangeStart = popupGiftIdeaWindow.getGiftPriceRangeStart();
        double giftPriceRangeEnd = popupGiftIdeaWindow.getGiftPriceRangeEnd();

        popupGiftIdeaWindow.clickGeneratePotentialGiftsButton();

        Assert.assertTrue(popupGiftIdeaWindow.isPotentialGiftsIsPresent(),
                "Potential gifts is not present");

        List<PotentialGift> potentialGifts = popupGiftIdeaWindow.getPotentialGifts();

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

        Assert.assertTrue(header.isSearchInputIsPresent(),
                "Search input is not present");

        Assert.assertEquals(header.getSearchInputPlaceholder(), "Пошук товарів",
                "Search input has an incorrect placeholder");

        Assert.assertTrue(homePage.isCategoryButtonPresent("Ігрова зона"),
                "Game zone button is not present");

        CategoryPage gameZonePage = homePage.clickCategoryButton("Ігрова зона");

        Assert.assertTrue(gameZonePage.isTitleElementIsPresent(),
                "Title element is not present");

        SearchPage gamesPage = gameZonePage.clickGamesButton("Ігри");

        Assert.assertTrue(gamesPage.areProductsCards(),
                "Search input is not present()");

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

        Assert.assertTrue(searchPage.isTitleElementIsPresent(),
                "Title element is not present");

        List<ProductCard> productCards = searchPage.getProductCards();
        ProductCard firstProductCard = productCards.get(0);

        String expectedProductTitle = firstProductCard.getTitleText();
        double expectedProductPrice = firstProductCard.getProductPrice();

        Assert.assertTrue(firstProductCard.isAddToCartButtonIsPresent(),
                "Add to cart button is not present");
        PopupCartWindow popupWindow = firstProductCard.addToCartButton();
        Assert.assertTrue(searchPage.isSuccessAddedButtonIsPresent(),
                "Success added button is not present");

        Assert.assertTrue(popupWindow.isButtonToCartPresent(),
                "Button to cart is not present");

        CartPage cartPage = popupWindow.clickButtonToCartPage();
        cartPage.areProductsPresent();

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

        Assert.assertTrue(searchPage.isTitleElementIsPresent(), "Title is not present");

        List<ProductCard> productCards = searchPage.getProductCards();

        for (ProductCard card : productCards) {
            softAssert.assertTrue(card.getTitleText().toLowerCase().contains(brandName.toLowerCase()),
                    String.format("Product with name '%s' doesn't contain the brand name in its title",
                            card.getTitleText()));
        }

        softAssert.assertAll();
    }
}
