package com.solvd.mobileTests;

import com.solvd.android.HomePage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
    public void verifyHomePageTest(String task) {
        HomePage homePage = new HomePage(getDriver());

        homePage.addNewTask(task);
        Assert.assertEquals(homePage.isTaskExists(task), task,
                "Names of the tasks doesn't match");
        homePage.deleteTask(task);
    }

    @Test(dataProvider = "categories")
    public void verifyAddNewCategory(String category) {
        HomePage homePage = new HomePage(getDriver());

        homePage.addNewCategory(category);
        Assert.assertTrue(homePage.isCategoryPresent(category));
        homePage.deleteCategory(category);

        Assert.assertFalse(homePage.isCategoryPresent(category));
        homePage.clickBackToHomePageButton();
    }
}