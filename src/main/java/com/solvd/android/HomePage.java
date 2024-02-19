package com.solvd.android;

import com.solvd.android.components.Category;
import com.solvd.android.components.Task;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    public void typeToInputTextBar(String string) {
        inputTextBar.type(string);
    }

    public void clickAddTaskButton() {
        addTaskButton.click();
    }

    public boolean isAddTaskButtonPresent() {
        return addTaskButton.isElementPresent();
    }

    public String getInputTextBarText() {
        return inputTextBar.getText();
    }

    public Task getTaskByName(String taskName) {
        return getTasks().stream()
                .filter(task -> task.getTaskText().equalsIgnoreCase(taskName))
                .findFirst().orElse(null);
    }

    public boolean isTaskPresent(String taskName) {
        waitUntilTasksPresent();
        return tasks.stream()
                .anyMatch(task -> task.getTaskText().equalsIgnoreCase(taskName));
    }

    public TaskDetailsPage taskClick(String taskName) {
        Task taskToClick = tasks.stream()
                .filter(task -> task.getTaskText().equalsIgnoreCase(taskName))
                .findFirst().orElse(null);
        return taskToClick.clickTaskText();
    }

    public void waitUntilTasksPresent() {
        waitUntil((value) -> !tasks.isEmpty(), 3);
    }

    public Task clickOnSubmitButton() {
        submitButton.click();
        return new Task(getDriver());
    }

    public ManageCategoriesPage clickManageCategoriesButton() {
        manageCategoriesButton.click();
        return new ManageCategoriesPage(getDriver());
    }

    public boolean isManageCategoriesButtonPresent() {
        return manageCategoriesButton.isElementPresent();
    }

    public void clickDetailsButton() {
        detailsButton.click();
    }

    public boolean isDetailsButtonPresent() {
        return detailsButton.isElementPresent();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void clickBottomPanelButton(String bottomPanelButtonId) {
        bottomPanelButton.format(bottomPanelButtonId).click();
    }

    public boolean isCompletedTodayLabelPresent() {
        return completedTodayLabel.isElementPresent();
    }

    public void clickDeleteSubmitButton() {
        deleteSubmitButton.click();
    }

    public void clickGreenFlagButton() {
        greenFlagButton.click();
    }

    public boolean isInputTextBarPresent() {
        return inputTextBar.isElementPresent();
    }
}
