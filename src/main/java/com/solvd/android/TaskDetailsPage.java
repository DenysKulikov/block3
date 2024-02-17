package com.solvd.android;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TaskDetailsPage extends AbstractPage {
    @FindBy(xpath = "//*[contains(@resource-id, 'd/detail_delete')]")
    private ExtendedWebElement taskDetailsDeleteButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/dialog_confirm')]")
    private ExtendedWebElement confirmDeleteTaskButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/task_detail_more')]")
    private ExtendedWebElement taskDetailsButton;

    public TaskDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void clickTaskDetailsDeleteButton() {
        taskDetailsDeleteButton.click();
    }

    public void clickConfirmDeleteTaskButton() {
        confirmDeleteTaskButton.click();
    }

    public void clickTaskDetailsButton() {
        taskDetailsButton.click();
    }

    public void deleteTask() {
        clickTaskDetailsButton();
        clickTaskDetailsDeleteButton();
        clickConfirmDeleteTaskButton();
    }
}
