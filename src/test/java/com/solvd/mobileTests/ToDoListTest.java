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

    @Test
    public void verifyAddNewCategory() {
        HomePage homePage = new HomePage(getDriver());

        homePage.addNewCategory("Home");
        Assert.assertTrue(homePage.isCategoryPresent("Home"));
        homePage.deleteCategory("Home");

        Assert.assertFalse(homePage.isCategoryPresent("Home"));
        homePage.clickBackToHomePageButton();
    }
}