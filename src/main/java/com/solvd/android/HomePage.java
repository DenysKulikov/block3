package com.solvd.android;

import com.solvd.android.components.Category;
import com.solvd.android.components.Task;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class HomePage extends AbstractPage implements IMobileUtils {
    @FindBy(xpath = "//*[contains(@resource-id, 'id/iv_task_add')]")
    private ExtendedWebElement addTaskButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/task_create_input')]")
    private ExtendedWebElement inputTextBar;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/task_create_btn')]")
    private ExtendedWebElement submitButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/task_slideLinearLayout')]")
    private List<Task> tasks;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/tag_management')]")
    private ExtendedWebElement detailsButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/popup_tv') and @text=\"Manage Categories\"]")
    private ExtendedWebElement manageCategoriesButton;

    @FindBy(xpath = "//*[contains(@resource-id, '%s')]")
    private ExtendedWebElement bottomPanelButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/label_text')]")
    private ExtendedWebElement completedTodayLabel;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/dialog_confirm')]")
    private ExtendedWebElement deleteSubmitButton;

    @FindBy(xpath = "(//*[contains(@resource-id, 'id/symbol_icon4')])[1]")
    private ExtendedWebElement greenFlagButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void addNewTask(String taskText) {
        addTaskButton.click();

        sendKeysToInputTextBar(taskText);
    }

    public void deleteTask(String taskName) {
        findTaskByName(taskName).get().taskClick();
        TaskDetailsPage taskDetailsPage = new TaskDetailsPage(getDriver());

        taskDetailsPage.clickTaskDetailsButton();
        taskDetailsPage.clickTaskDetailsDeleteButton();
        taskDetailsPage.clickConfirmDeleteTaskButton();
    }

    public Optional<Task> findTaskByName(String taskName) {
        isTasksPresent();
        return getTasks().stream().
                filter(task -> task.getTaskTex().equals(taskName))
                .findFirst();
    }

    private void sendKeysToInputTextBar(String text) {
        inputTextBar.type(text);
        clickOnSubmitButton();
    }

    private boolean isTasksPresent() {
        return waitUntil(value -> !tasks.isEmpty(), 3);
    }

    private void clickOnSubmitButton() {
        submitButton.click();
    }

    private void clickManageCategoriesButton() {
        manageCategoriesButton.click();
    }

    public boolean isCategoryPresent(String categoryName) {
        ManageCategoriesPage manageCategoriesPage = new ManageCategoriesPage(getDriver());

        return manageCategoriesPage.getCategories().stream()
                .anyMatch(category -> category.getCategoryName().equals(categoryName));
    }

    public void addNewCategory(String category) {
        ManageCategoriesPage manageCategoriesPage = new ManageCategoriesPage(getDriver());

        clickDetailsButton();
        clickManageCategoriesButton();
        manageCategoriesPage.clickCreateNewCategoryButton();
        manageCategoriesPage.typeToInputBar(category);
        manageCategoriesPage.clickSaveCategoryButton();
    }

    public void deleteCategory(String categoryName) {
        ManageCategoriesPage manageCategoriesPage = new ManageCategoriesPage(getDriver());
        List<Category> categories = manageCategoriesPage.getCategories();

        Optional<Category> categoryToDelete = categories.stream()
                .filter(category -> category.getCategoryName().toLowerCase().equals(categoryName.toLowerCase()))
                .findFirst();

        manageCategoriesPage.deleteCategory(categoryToDelete.get());
    }

    public void clickDetailsButton() {
        detailsButton.click();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean isTaskPresent(String taskName) {
        isTasksPresent();
        return getTasks().stream()
                .anyMatch(task -> task.getTaskTex().equals(taskName));
    }

    public void makeTaskComplete(String taskName) {
        Optional<Task> task = findTaskByName(taskName);
        task.get().clickMakeTaskCompleteButton();
    }

    public void clickBottomPanelButton(String bottomPanelButtonId) {
        bottomPanelButton.format(bottomPanelButtonId).click();
    }

    public boolean isCompletedTodayLabelIsPresent() {
        return completedTodayLabel.isElementPresent();
    }

    public void swipeLeftToDeleteTask(String taskName) {
        Optional<Task> task = findTaskByName(taskName);

        if (task.isPresent()) {
            Task taskElement = task.get();

            taskElement.swipeLeftFlag();
            taskElement.clickDeleteButton();
            clickDeleteSubmitButton();
        } else {
            // Handle the case when the task is not found
            System.out.println("Task not found: " + taskName);
        }
    }

    public void clickDeleteSubmitButton() {
        isDeleteSubmitButtonIsPresent();
        deleteSubmitButton.click();
    }

    public boolean isDeleteSubmitButtonIsPresent() {
        return deleteSubmitButton.isPresent();
    }

    public void clickGreenFlagButton() {
        greenFlagButton.click();
    }

    public void pendTask(String taskName) {
        Optional<Task> task = findTaskByName(taskName);
        task.get().clickFlag();
        clickGreenFlagButton();
    }
}
