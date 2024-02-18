package com.solvd.android.components;

import com.solvd.android.TaskDetailsPage;
import com.zebrunner.carina.utils.android.IAndroidUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Task extends AbstractUIObject implements IAndroidUtils {
    @FindBy(xpath = ".//*[@text ='%s']")
    private ExtendedWebElement taskName;

    @FindBy(xpath = "//android.view.View[contains(@resource-id, 'id/task_check')]")
    private ExtendedWebElement makeTaskCompleteButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/task_symbol')]")
    private ExtendedWebElement flag;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/task_delete_layout')]")
    private ExtendedWebElement deleteButton;

    public Task(WebDriver driver) {
        super(driver);
    }

    public Task(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public TaskDetailsPage taskClick(String task) {
        taskName.format(task).click();
        return new TaskDetailsPage(getDriver());
    }

    public void clickMakeTaskCompleteButton() {
        makeTaskCompleteButton.click();
    }

    public void swipeLeftFlag(String task) {
        int startX = flag.getLocation().getX();
        int startY = flag.getLocation().getY();
        int endY = taskName.format(task).getLocation().getY();
        int endX = taskName.format(task).getLocation().getX();

        swipe(startX, startY, endX, endY, 1000);
    }

    public String getTaskText(String task) {
        return taskName.format(task).getText();
    }

    public void swipeLeftToClickDeleteButton(String taskName) {
        swipeLeftFlag(taskName);
        clickDeleteButton();
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }

    public void clickFlag() {
        flag.click();
    }
}
