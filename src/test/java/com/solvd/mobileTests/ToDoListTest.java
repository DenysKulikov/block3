package com.solvd.mobileTests;

import com.solvd.android.HomePage;
import com.solvd.android.ManageCategoriesPage;
import com.solvd.android.MinePage;
import com.solvd.android.TaskDetailsPage;
import com.solvd.android.components.BottomPanelButton;
import com.solvd.android.components.Category;
import com.solvd.android.components.Task;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

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
        Assert.assertTrue(homePage.isDetailsButtonPresent(),
                "App is not on the home page");

        homePage.clickAddTaskButton();
        Assert.assertTrue(homePage.isInputTextBarPresent(),
                "The input text bar is not appeared");

        homePage.typeToInputTextBar(taskName);
        homePage.clickOnSubmitButton();
        Task task = new Task(getDriver());

        Assert.assertTrue(homePage.isTaskPresent(taskName),
                "Task with expected name doesn't present");

        TaskDetailsPage taskDetailsPage = task.taskClick(taskName);
        taskDetailsPage.deleteTask();
        Assert.assertFalse(homePage.isTaskPresent(taskName),
                "The task was not deleted");
    }

    @Test(dataProvider = "categories")
    public void verifyAddNewCategoryTest(String categoryName) {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isDetailsButtonPresent(),
                "App is not on the home page");

        homePage.clickDetailsButton();
        Assert.assertTrue(homePage.isManageCategoriesButtonPresent(),
                "A pop-up window doe not appeared");

        ManageCategoriesPage manageCategoriesPage = homePage.clickManageCategoriesButton();
        List<Category> categories = manageCategoriesPage.getCategories();
        manageCategoriesPage.waitUntilCategoriesPresent();
        Assert.assertFalse(categories.isEmpty(),
                "Manage Categories Page does not loaded");

        manageCategoriesPage.clickCreateNewCategoryButton();
        Assert.assertTrue(manageCategoriesPage.isInputLineIsPresent(),
                "Input Line is not Present");

        manageCategoriesPage.typeToInputBar(categoryName);
        Category category = manageCategoriesPage.clickSaveCategoryButton();

        Assert.assertTrue(category.isCategoryPresent(),
                "Category is not present");

        Category categoryToDelete = categories.get(categories.size() - 1);

        manageCategoriesPage.deleteCategory(categoryToDelete);

        Assert.assertFalse(categoryToDelete.isCategoryPresent(),
                "Category has not been deleted");

        manageCategoriesPage.clickBackToHomePageButton();
        Assert.assertTrue(homePage.isDetailsButtonPresent(),
                "App is not on the home page");
    }

    @Test(dataProvider = "taskNames")
    public void verifyTaskCanBeCompletedTextTest(String taskName) {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isDetailsButtonPresent(),
                "App is not on the home page");

        homePage.clickAddTaskButton();
        homePage.typeToInputTextBar(taskName);
        homePage.clickOnSubmitButton();
        Task task = new Task(getDriver());

        Assert.assertTrue(homePage.isTaskPresent(taskName),
                "Task is not present");
        task.clickMakeTaskCompleteButton();

        Assert.assertTrue(homePage.isCompletedTodayLabelPresent(),
                "Completed Today Label is not Present");
        task.swipeLeftToClickDeleteButton(taskName);
        homePage.clickDeleteSubmitButton();
        Assert.assertFalse(homePage.isTaskPresent(taskName),
                "The task was not deleted");
    }

    @Test(dataProvider = "taskNames")
    public void verifyTaskCanBeCompletedMinePageTest(String taskName) {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isDetailsButtonPresent(),
                "App is not on the home page");

        MinePage minePage = new MinePage(getDriver());

        homePage.clickAddTaskButton();
        homePage.typeToInputTextBar(taskName);
        homePage.clickOnSubmitButton();
        Task task = new Task(getDriver());

        Assert.assertTrue(homePage.isTaskPresent(taskName),
                "Task is not present");
        homePage.clickBottomPanelButton(BottomPanelButton.MINE.getButtonId());
        int completedTasksBeforeOperations = minePage.getCompletedTasks();
        homePage.clickBottomPanelButton(BottomPanelButton.TUSKS.getButtonId());

        task.clickMakeTaskCompleteButton();

        homePage.clickBottomPanelButton(BottomPanelButton.MINE.getButtonId());
        int completedTasksAfterOperations = minePage.getCompletedTasks();
        Assert.assertTrue(completedTasksAfterOperations > completedTasksBeforeOperations,
                "Completed Tasks After Operations is not bigger than completed Tasks Before Operations");

        homePage.clickBottomPanelButton(BottomPanelButton.TUSKS.getButtonId());
        TaskDetailsPage taskDetailsPage = task.taskClick(taskName);
        taskDetailsPage.deleteTask();
        Assert.assertFalse(homePage.isTaskPresent(taskName),
                "The task was not deleted");
    }

    @Test(dataProvider = "taskNames")
    public void verifyIfCalendarPageShowsPendingTasksTest(String taskName) {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isDetailsButtonPresent(),
                "App is not on the home page");

        MinePage minePage = new MinePage(getDriver());

        homePage.clickBottomPanelButton(BottomPanelButton.MINE.getButtonId());
        int pendingTasksBeforeOperations = minePage.getPendingTasks();
        homePage.clickBottomPanelButton(BottomPanelButton.TUSKS.getButtonId());

        homePage.clickAddTaskButton();
        homePage.typeToInputTextBar(taskName);
        homePage.clickOnSubmitButton();
        Task task = new Task(getDriver());

        task.clickFlag();
        homePage.clickGreenFlagButton();

        homePage.clickBottomPanelButton(BottomPanelButton.MINE.getButtonId());
        int pendingTasksAfterOperations = minePage.getPendingTasks();
        Assert.assertTrue(pendingTasksAfterOperations > pendingTasksBeforeOperations,
                "Pending Tasks Before operations is bigger than pending tasks after operations");

        homePage.clickBottomPanelButton(BottomPanelButton.TUSKS.getButtonId());
        TaskDetailsPage taskDetailsPage = task.taskClick(taskName);
        taskDetailsPage.deleteTask();
        Assert.assertFalse(homePage.isTaskPresent(taskName),
                "The task was not deleted");
    }
}