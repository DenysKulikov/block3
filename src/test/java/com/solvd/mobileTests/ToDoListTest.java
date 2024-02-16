package com.solvd.mobileTests;

import com.solvd.android.HomePage;
import com.solvd.android.ManageCategoriesPage;
import com.solvd.android.MinePage;
import com.solvd.android.components.BottomPanelButton;
import com.solvd.android.components.Task;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

public class ToDoListTest extends AbstractTest implements IMobileUtils {
    @DataProvider
    public Object[][] taskNames() {
        return new Object[][] {
                {"do the dishes"}
        };
    }

    @DataProvider
    public Object[][] categories() {
        return new Object[][] {
                {"Home"}
        };
    }

    @BeforeTest
    public void startToDoApp() {
        startApp("todolist.scheduleplanner.dailyplanner.todo.reminders");
    }

    @Test(dataProvider = "taskNames")
    public void verifyHomePageTest(String taskName) {
        HomePage homePage = new HomePage(getDriver());

        homePage.addNewTask(taskName);
        Assert.assertEquals(homePage.findTaskByName(taskName).get().getTaskTex(), taskName,
                "Names of the tasks doesn't match");
        homePage.deleteTask(taskName);
    }

    @Test(dataProvider = "categories")
    public void verifyAddNewCategoryTest(String category) {
        HomePage homePage = new HomePage(getDriver());
        ManageCategoriesPage manageCategoriesPage = new ManageCategoriesPage(getDriver());

        homePage.addNewCategory(category);
        Assert.assertTrue(homePage.isCategoryPresent(category),
                "Category is not present");
        homePage.deleteCategory(category);

        Assert.assertFalse(homePage.isCategoryPresent(category),
                "Category has not been deleted");
        manageCategoriesPage.clickBackToHomePageButton();
    }

    @Test(dataProvider = "taskNames")
    public void verifyTaskCanBeCompletedTextTest(String taskName) {
        HomePage homePage = new HomePage(getDriver());

        homePage.addNewTask(taskName);
        Assert.assertTrue(homePage.isTaskPresent(taskName),
                "Task is not present");
        Optional<Task> foundTask = homePage.findTaskByName(taskName);
        foundTask.get().clickMakeTaskCompleteButton();

        Assert.assertTrue(homePage.isCompletedTodayLabelIsPresent());
        homePage.swipeLeftToDeleteTask(taskName);
        Assert.assertFalse(homePage.isTaskPresent(taskName));
    }

    @Test(dataProvider = "taskNames")
    public void verifyTaskCanBeCompletedMinePageTest(String taskName) {
        HomePage homePage = new HomePage(getDriver());
        MinePage minePage = new MinePage(getDriver());

        homePage.addNewTask(taskName);
        Assert.assertTrue(homePage.isTaskPresent(taskName),
                "Task is not present");
        Optional<Task> foundTask = homePage.findTaskByName(taskName);
        homePage.clickBottomPanelButton(BottomPanelButton.MINE.getButtonId());
        int completedTasksBeforeOperations = minePage.getCompletedTasks();
        homePage.clickBottomPanelButton(BottomPanelButton.TUSKS.getButtonId());

        foundTask.get().clickMakeTaskCompleteButton();

        homePage.clickBottomPanelButton(BottomPanelButton.MINE.getButtonId());
        int completedTasksAfterOperations = minePage.getCompletedTasks();
        Assert.assertTrue(completedTasksAfterOperations > completedTasksBeforeOperations,
                "Completed Tasks After Operations is not bigger than completed Tasks Before Operations");

        homePage.clickBottomPanelButton(BottomPanelButton.TUSKS.getButtonId());
        homePage.deleteTask(taskName);
    }

    @Test(dataProvider = "taskNames")
    public void verifyIfCalendarPageShowsPendingTasksTest(String taskName) {
        HomePage homePage = new HomePage(getDriver());
        MinePage minePage = new MinePage(getDriver());

        homePage.clickBottomPanelButton(BottomPanelButton.MINE.getButtonId());
        int pendingTasksBeforeOperations = minePage.getPendingTasks();
        homePage.clickBottomPanelButton(BottomPanelButton.TUSKS.getButtonId());

        homePage.addNewTask(taskName);
        homePage.pendTask(taskName);

        homePage.clickBottomPanelButton(BottomPanelButton.MINE.getButtonId());
        int pendingTasksAfterOperations = minePage.getPendingTasks();
        Assert.assertTrue(pendingTasksAfterOperations > pendingTasksBeforeOperations,
                "Pending Tasks Before operations is bigger than pending tasks after operations");

        homePage.clickBottomPanelButton(BottomPanelButton.TUSKS.getButtonId());
        homePage.deleteTask(taskName);
    }
}