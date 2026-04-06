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
import java.util.HashMap;
import java.util.Map;

import static utils.PropertiesReader.getProperty;

public class RegistrationApiTests implements BaseApi {

    private User createValidUser() {
        String password = getProperty("base.properties", "password");
        String uniqueEmail = "user" + System.currentTimeMillis() + "@gmail.com";
        return new User(uniqueEmail, password);
    }

    private User createDuplicateUser() {
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
    public void registrationPositiveApiTest() {
        User user = createValidUser();

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(
                    response.code(),
                    200,
                    "Expected status code 200, but found " + response.code()
            );
        }
    }

    @Test(groups = "negative")
    public void registrationNegativeWrongPasswordApiTest() {
        User user = createValidUser();
        user.setPassword("wrong password");

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(
                    response.code(),
                    400,
                    "Expected status code 400, but found " + response.code()
            );
        }
    }

    @Test(groups = "negative")
    public void registrationNegativeWithoutEmailApiTest() {
        User user = createValidUser();
        user.setUsername(null);

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(
                    response.code(),
                    400,
                    "Expected status code 400, but found " + response.code()
            );
        }
    }

    @Test(groups = "negative")
    public void registrationNegativeWithoutPasswordApiTest() {
        User user = createValidUser();
        user.setPassword(null);

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(
                    response.code(),
                    400,
                    "Expected status code 400, but found " + response.code()
            );
        }
    }

    @Test(groups = "negative")
    public void registrationNegativeWithWrongEndpointApiTest() {
        User user = createValidUser();

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(
                    response.code(),
                    401,
                    "Expected status code 401, but found " + response.code()
            );
        }
    }

    @Test(groups = "negative")
    public void registrationNegativePasswordIsEmptyApiTest() {
        User user = createValidUser();
        user.setPassword("");

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(
                    response.code(),
                    400,
                    "Expected status code 400, but found " + response.code()
            );
        }
    }

    @Test(groups = "negative")
    public void registrationNegativeDuplicateUserApiTest() {
        User user = createDuplicateUser();

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(
                    response.code(),
                    409,
                    "Expected status code 409, but found " + response.code()
            );
        }
    }

    @Test(groups = "negative")
    public void registrationNegativeWrongUrlApiTest() {
        User user = createValidUser();

        try (Response response = postRequest(BASE_URL + "/v1/regis", user, JSON)) {
            Assert.assertEquals(
                    response.code(),
                    403,
                    "Expected status code 403, but found " + response.code()
            );
        }
    }

    @Test(groups = "negative")
    public void registrationNegativeWrongFormatTextApiTest() {
        User user = createValidUser();

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, TEXT)) {
            Assert.assertEquals(
                    response.code(),
                    500,
                    "Expected status code 500, but found " + response.code()
            );
        }
    }

    @Test(groups = "negative")
    public void registrationNegativeWrongUserKeyApiTest() throws IOException {
        User user = createValidUser();

        Map<String, String> invalidJson = new HashMap<>();
        invalidJson.put("invalidUsername", user.getUsername());
        invalidJson.put("password", user.getPassword());

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, invalidJson, JSON)) {
            String responseBody = response.body().string();

            System.out.println("Status code: " + response.code());
            System.out.println("Response body: " + responseBody);

            Assert.assertEquals(
                    response.code(),
                    500,
                    "Expected status code 500, but found " + response.code()
            );
        }
    }
}