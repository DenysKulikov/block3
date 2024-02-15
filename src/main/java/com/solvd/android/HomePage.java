package com.solvd.android;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Optional;

public class HomePage extends AbstractPage implements IMobileUtils {
    @FindBy(xpath = "//*[contains(@resource-id, 'id/iv_task_add')]")
    private ExtendedWebElement addTaskButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/task_create_input')]")
    private ExtendedWebElement inputTextBar;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/task_create_btn')]")
    private ExtendedWebElement submitButton;

    @FindBy(xpath = "//*[contains(@text, '%s')]")
    private ExtendedWebElement task;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/task_detail_more')]")
    private ExtendedWebElement taskDetailsButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'd/detail_delete')]")
    private ExtendedWebElement taskDetailsDeleteButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/dialog_confirm')]")
    private ExtendedWebElement confirmDeleteTaskButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/tag_management')]")
    private ExtendedWebElement detailsButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/popup_tv') and @text=\"Manage Categories\"]")
    private ExtendedWebElement manageCategoriesButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/create_category_text')]")
    private ExtendedWebElement createNewCategoryButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/dialog_input')]")
    private ExtendedWebElement input;
    @FindBy(xpath = "//*[contains(@resource-id, 'id/dialog_confirm')]")
    private ExtendedWebElement saveCategoryButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/toolbar_back')]")
    private ExtendedWebElement backToHomePageButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/category_text') and @text=\"Wishlist\"]")
    private ExtendedWebElement wishListCategory;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/categorymag_layout')]/android.view.ViewGroup")
    private List<Category> categories;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/dialog_confirm')]")
    private ExtendedWebElement confirmDeleteCategory;

    @FindBy(xpath = ".//*[@text=\"Delete\"]")
    private ExtendedWebElement deleteCategoryButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void addNewTask(String taskText) {
        addTaskButton.click();

        sendKeysToInputTextBar(taskText);
    }

    public void deleteTask(String taskText) {
        task.format(taskText).click();
        clickTaskDetailsButton();
        clickTaskDetailsDeleteButton();
        clickConfirmDeleteTaskButton();
    }

    private void sendKeysToInputTextBar(String text) {
        inputTextBar.type(text);
        clickOnSubmitButton();
    }

    private void clickOnSubmitButton() {
        submitButton.click();
    }

    public String isTaskExists(String taskText) {
        return task.format(taskText).getText();
    }

    private void clickTaskDetailsButton() {
        taskDetailsButton.click();
    }

    private void clickTaskDetailsDeleteButton() {
        taskDetailsDeleteButton.click();
    }

    private void clickConfirmDeleteTaskButton() {
        confirmDeleteTaskButton.click();
    }

    private void clickDetailsButton() {
        detailsButton.click();
    }

    private void clickManageCategoriesButton() {
        manageCategoriesButton.click();
    }

    private void clickCreateNewCategoryButton() {
        createNewCategoryButton.click();
    }

    private void clickSaveCategoryButton() {
        saveCategoryButton.click();
    }

    public void clickBackToHomePageButton() {
        backToHomePageButton.click();
    }

    public boolean isCategoryPresent(String categoryName) {
        return categories.stream()
                .anyMatch(category -> category.getCategoryText().equals(categoryName));
    }

    public void addNewCategory(String category) {
        clickDetailsButton();
        clickManageCategoriesButton();
        clickCreateNewCategoryButton();
        input.type(category);
        clickSaveCategoryButton();
    }

    private void deleteCategory(Category category) {
        category.clickCategoryProperties();
        deleteCategoryButton.click();
        confirmDeleteCategory.click();
    }

    public void deleteCategory(String categoryName) {
        Optional<Category> categoryToDelete = categories.stream()
                .filter(category -> category.getCategoryText().toLowerCase().equals(categoryName.toLowerCase()))
                .findFirst();

        deleteCategory(categoryToDelete.get());
    }
}
