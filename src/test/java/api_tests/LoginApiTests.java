package api_tests;

import dto.User;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.PropertiesReader.getProperty;

public class LoginApiTests implements BaseApi {

    private User createValidUser() {
        return new User(
                getProperty("base.properties", "login"),
                getProperty("base.properties", "password")
        );
    }

    private Response postRequest(String url, Object body, MediaType mediaType) {
        RequestBody requestBody = RequestBody.create(GSON.toJson(body), mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            return OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void loginPositiveTest() {
        User user = createValidUser();

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 200);
        }
    }

    @Test
    public void loginNegativeTestWithoutAtEmailApiTest() {
        User user = new User(
                "loginyoh.com",
                getProperty("base.properties", "password")
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestIsBlankApiTest() {
        User user = new User(" ", " ");

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestWithTwoDotsApiTest() {
        User user = new User(
                "login@yoho..com",
                getProperty("base.properties", "password")
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestPasswordIsBlankApiTest() {
        User user = new User(
                getProperty("base.properties", "login"),
                ""
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestEmailIsBlankApiTest() {
        User user = new User(
                "",
                getProperty("base.properties", "password")
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestEmailHasTwoAtsApiTest() {
        User user = new User(
                "login@@yoho.com",
                getProperty("base.properties", "password")
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestWrongEmailApiTest() {
        User user = new User(
                "login@gmaisss.il",
                getProperty("base.properties", "password")
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestWithoutUpperCaseApiTest() {
        User user = new User(
                getProperty("base.properties", "login"),
                "parol12!"
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestWithoutLowerCaseApiTest() {
        User user = new User(
                getProperty("base.properties", "login"),
                "PAROL12!"
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestWithoutNumberApiTest() {
        User user = new User(
                getProperty("base.properties", "login"),
                "Parolll!"
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestWithoutSpecialSymbolApiTest() {
        User user = new User(
                getProperty("base.properties", "login"),
                "Parol12l"
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeTestWithShortPasswordApiTest() {
        User user = new User(
                getProperty("base.properties", "login"),
                "Parl12!"
        );

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401);
        }
    }

    @Test
    public void loginNegativeWrongMediaTypeApiTest() {
        User user = createValidUser();

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, TEXT)) {
            Assert.assertEquals(
                    response.code(),
                    200,
                    "BUG: server returned unexpected status for wrong media type"
            );
        }
    }

    @Test
    public void loginNegativeWrongEndpointApiTest() {
        User user = createValidUser();

        try (Response response = postRequest(BASE_URL + ADD_NEW_CONTACT_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 403);
        }
    }
}