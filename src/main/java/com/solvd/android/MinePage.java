package com.solvd.android;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class MinePage extends AbstractPage {
    @FindBy(xpath = "//*[contains(@resource-id, 'id/finished_tasks_number')]")
    private ExtendedWebElement completedTasks;

    @FindBy(xpath = "//*[contains(@resource-id, 'id/pending_tasks_number')]")
    private ExtendedWebElement pendingTasks;

    public MinePage(WebDriver driver) {
        super(driver);
    }

    public int getCompletedTasks() {
        return Integer.parseInt(completedTasks.getText());
    }

    public int getPendingTasks() {
        return Integer.parseInt(pendingTasks.getText());
    }
}
