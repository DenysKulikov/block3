package com.solvd.android;

import com.solvd.android.components.Category;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ManageCategoriesPage extends AbstractPage {
    @FindBy(xpath = "//*[contains(@resource-id, 'id/create_category_text')]")
    private ExtendedWebElement createNewCategoryButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/dialog_input')]")
    private ExtendedWebElement input;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/dialog_confirm')]")
    private ExtendedWebElement saveCategoryButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/toolbar_back')]")
    private ExtendedWebElement backToHomePageButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/categorymag_layout')]/android.view.ViewGroup")
    private List<Category> categories;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/dialog_confirm')]")
    private ExtendedWebElement confirmDeleteCategory;

    @FindBy(xpath = ".//*[@text='Delete']")
    private ExtendedWebElement deleteCategoryButton;

    public ManageCategoriesPage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateNewCategoryButton() {
        createNewCategoryButton.click();
    }

    public Category clickSaveCategoryButton() {
        saveCategoryButton.click();
        return new Category(getDriver());
    }

    public void clickBackToHomePageButton() {
        backToHomePageButton.click();
    }

    public void deleteCategory(Category category) {
        category.clickCategoryPropertiesButton();
        deleteCategoryButton.click();
        confirmDeleteCategory.click();
    }

    public void waitUntilCategoriesPresent() {
        waitUntil((value) -> !categories.isEmpty(), 3);
    }

    public boolean isInputLineIsPresent() {
        return input.isPresent();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void typeToInputBar(String category) {
        input.type(category);
    }
}
