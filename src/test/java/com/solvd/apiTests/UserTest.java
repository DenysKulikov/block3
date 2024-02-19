package com.solvd.apiTests;

import com.solvd.api.*;
import com.solvd.api.domain.User;
import com.zebrunner.carina.api.apitools.validation.JsonComparatorContext;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTest {
    @Test
    public void verifyGetUserByIdTest() {
        User user = new User();
        user.setId(1);
        user.setFirstName("john");
        user.setLastName("doe");

        GetUserByIdMethod getUserById = new GetUserByIdMethod(user.getId());
        getUserById.addProperty("user", user);

        getUserById.callAPIExpectSuccess();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<String>withPredicate("phonePredicate", phone -> isPhoneValid(phone));

        getUserById.validateResponse(comparatorContext);
    }

    @Test
    public void verifyDeleteUserTest() {
        User user = new User();
        user.setId(7);

        DeleteUserMethod deleteUser = new DeleteUserMethod(user.getId());

        deleteUser.callAPIExpectSuccess();
    }

    @Test
    public void verifyCreateUserTest() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        CreateUserMethod createUser = new CreateUserMethod();
        createUser.addProperty("user", user);

        createUser.callAPIExpectSuccess();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<String>withPredicate("phonePredicate", phone -> isPhoneValid(phone));

        createUser.validateResponse(comparatorContext);
    }

    @Test
    public void verifyUpdateUserPutTest() {
        User user = new User();
        user.setId(7);
        user.setFirstName("John");
        user.setLastName("Doe");

        UpdateUserPutMethod updateUserPut = new UpdateUserPutMethod(user.getId());
        updateUserPut.addProperty("user", user);

        updateUserPut.callAPIExpectSuccess();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<String>withPredicate("phonePredicate", phone -> isPhoneValid(phone));

        updateUserPut.validateResponse(comparatorContext);
    }

    @Test(enabled = true)
    public void verifyUpdateUserPatchTest() {
        User user = new User();
        user.setId(7);
        user.setFirstName("John");
        user.setLastName("Doe");

        UpdateUserPatchMethod updateUserPatch = new UpdateUserPatchMethod(user.getId());
        updateUserPatch.addProperty("user", user);

        updateUserPatch.callAPIExpectSuccess();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<String>withPredicate("phonePredicate", phone -> isPhoneValid(phone));

        updateUserPatch.validateResponse(comparatorContext);
    }

    private static boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern.compile("^\\d{1}-\\d{3}-\\d{3}-\\d{4}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
