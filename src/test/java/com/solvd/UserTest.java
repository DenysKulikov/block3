package com.solvd;

import com.solvd.domain.User;
import com.zebrunner.carina.api.apitools.validation.JsonComparatorContext;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTest {
    @Test
    public void verifyGetUserByIdTest() {
        User user = new User();
        user.setId(1);
        user.setFirstName("john");
        user.setLastName("doe");

        GetUserById getUserById = new GetUserById(user.getId());
        getUserById.addProperty("user", user);

        getUserById.expectResponseStatus(HttpResponseStatusType.OK_200);
        getUserById.callAPI();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<String>withPredicate("phonePredicate", phone -> isPhoneValid(phone));

        getUserById.validateResponse(comparatorContext);
        getUserById.callAPI();
    }

    @Test
    public void verifyDeleteUser() {
        User user = new User();
        user.setId(7);

        DeleteUser deleteUser = new DeleteUser(user.getId());
        deleteUser.addProperty("user", user);

        deleteUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        deleteUser.callAPI();
    }

    @Test
    public void verifyCreateUser() {
        User user = new User();
        user.setFirstName("John");

        CreateUser createUser = new CreateUser(user.getFirstName());
        createUser.addProperty("user", user);

        createUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        createUser.callAPI();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<String>withPredicate("phonePredicate", phone -> isPhoneValid(phone));

        createUser.validateResponse(comparatorContext);
    }

    @Test
    public void verifyUpdateUserPut() {
        User user = new User();
        user.setId(7);
        user.setFirstName("John");

        UpdateUserPut updateUserPut = new UpdateUserPut(user.getId());
        updateUserPut.addProperty("user", user);

        updateUserPut.expectResponseStatus(HttpResponseStatusType.OK_200);
        updateUserPut.callAPI();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<String>withPredicate("phonePredicate", phone -> isPhoneValid(phone));

        updateUserPut.validateResponse(comparatorContext);
    }

    @Test
    public void verifyUpdateUserPatch() {
        User user = new User();
        user.setId(7);
        user.setFirstName("John");

        UpdateUserPatch updateUserPatch = new UpdateUserPatch(user.getId());
        updateUserPatch.addProperty("user", user);

        updateUserPatch.expectResponseStatus(HttpResponseStatusType.OK_200);
        updateUserPatch.callAPI();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<String>withPredicate("phonePredicate", phone -> isPhoneValid(phone));

        updateUserPatch.validateResponse(comparatorContext);
    }

    private static boolean isDateValid(String date) {
        try {
            ZonedDateTime.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern.compile("^\\d{1}-\\d{3}-\\d{3}-\\d{4}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
