package com.solvd;

import com.solvd.domain.User;
import com.zebrunner.carina.api.apitools.validation.JsonComparatorContext;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTest {
    @Test
    public void verifyGetUserBtIdTest() {
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

//        getUserById.validateResponse(comparatorContext);
        getUserById.callAPI();
    }

    private static boolean isPhoneValid(String phone) {
        // Use a regular expression to validate the phone number format
        Pattern pattern = Pattern.compile("^\\d{1}-\\d{3}-\\d{3}-\\d{4}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
