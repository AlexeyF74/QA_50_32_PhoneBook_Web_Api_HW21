//package api_tests;
//
//import dto.User;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import utils.BaseApi;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import static utils.PropertiesReader.getProperty;
//import static utils.UserFactory.positiveUser;
//
//
//public class RegistrationApiTests implements BaseApi {
//
//    @Test
//    public void registrationPositiveApiTest() {
//        User user = positiveUser();
//        System.out.println(user);
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 400);
////        System.out.println(response.code());
//    }
//
//    @Test
//    public void registrationNegative_Wrong_Password_ApiTest() {
//        User user = positiveUser();
//        user.setPassword("wrong password");
//        System.out.println(user);
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 400);
//    }
//
//    @Test
//    public void registrationNegativeTEst_WithoutEmail_ApiTest() {
//        User user = positiveUser();
//        user.setUsername(null);
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 400);
//    }
//
//    @Test
//    public void registrationNegativeTEst_Withoutpassword_ApiTest() {
//        User user = positiveUser();
//        user.setPassword(null);
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        Assert.assertEquals(response.code(), 400);
//    }
//
//    @Test
//    public void registrationNegative_WithWrongEndpoint_ApiTest() {
//        User user = positiveUser();
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + LOGIN_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 401);
//    }
//
//    @Test
//    public void registrationNegativeTest_passwordIsEmpty_ApiTest() {
//        User user = positiveUser();
//        user.setPassword("");
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 400);
//    }
//
//    @Test
//    public void registrationNegativeTest_DublicateUser_ApiTest() {
//        User user = new User(getProperty("base.properties", "login"),
//                getProperty("base.properties", "password"));
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 409);
//    }
//
//    @Test
//    public void registrationNegativeTest_WrongUrl_ApiTest() {
//        User user = positiveUser();
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + "/" + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 403);
//        System.out.println(response.code());
//
//    }
//
//    @Test
//    public void registrationNegative_Wrong_Format_Text_ApiTest() {
//        User user = positiveUser();
////        user.setPassword("wrong password");
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), TEXT);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 500);
//    }
//
//    @Test
//    public void registrationNegative_Wrong_Key_User_ApiTest() {
//        User user = positiveUser();
//        Map<String, String> invalidJson = new HashMap<>();
//        invalidJson.put("name", user.getUsername());
//        invalidJson.put("password", user.getPassword());
//        RequestBody requestBody = RequestBody.create(GSON.toJson(invalidJson), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 500);
//        System.out.println(response.code());
//    }
//    //    5 Test with pattern password (Parol12!)
//    @Test
//    public void registrationNegativePassword_WithOut_UpperCase_ApiTests() {
//        User user = new User(getProperty("base.properties","login"),
//                ("parol12!"));
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 400);
//    }
//    @Test
//    public void registrationNegativePassword_WithOut_LowerCase_ApiTests() {
//        User user = new User(getProperty("base.properties","login"),
//                ("PAROL12!"));
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 400);
//    }
//    @Test
//    public void registrationNegativePassword_WithOut_Number_ApiTests() {
//        User user = new User(getProperty("base.properties","login"),
//                ("Parolll!"));
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 400);
//    }
//    @Test
//    public void registrationNegativePassword_WithOut_SpecSymbol_ApiTests() {
//        User user = new User(getProperty("base.properties","login"),
//                ("Parol12l!"));
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 409);
//    }
//    @Test
//    public void registrationNegativePassword_LongWrong_ApiTests() {
//        User user = new User(getProperty("base.properties","login"),
//                ("Parl12l!"));
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 409);
//    }
//    @Test
//    public void registrationNegative_WithOutAt_Username_ApiTest() {
//        User user = new User(getProperty("loginyoh@.com", "login"),
//                getProperty("base.properties", "password"));
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 400);
//    }
//    @Test
//    public void registrationNegative_WithTwoDots_Username_ApiTest() {
//        User user = new User(getProperty("loginy.oh.com", "login"),
//                getProperty("base.properties", "password"));
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 400);
//    }
//
//    @Test
//    public void registrationNegativeTest_SubstitutionLoginPassword_ApiTest() {
//        User user = new User(getProperty("base.properties", "password"),
//                getProperty("base.properties", "login"));
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URL + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 400);
//    }
//
//    @Test
//    public void registrationNegativeWrongBaseUrl_ApiTests() {
//        User user = new User(getProperty("base.properties","login"),
//                getProperty("base.properties", "password"));
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//        Request request = new Request.Builder()
//                .url(BASE_URLWRONG + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//        Response response;
//        try {
//            response = OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Assert.assertEquals(response.code(), 409);
//    }
//}
//package api_tests;
//
//import dto.User;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import utils.BaseApi;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import static utils.UserFactory.positiveUser;
//import static utils.PropertiesReader.getProperty;
//
//public class RegistrationApiTests implements BaseApi {
//
//    private User createValidUser() {
//        String password = getProperty("base.properties", "password");
//        String uniqueEmail = "user" + System.currentTimeMillis() + "@gmail.com";
//        return new User(uniqueEmail, password);
//    }
//
//    private User createDuplicateUser() {
//        return new User(
//                getProperty("base.properties", "login"),
//                getProperty("base.properties", "password")
//        );
//    }
//
//    private Response postRequest(String url, Object body, okhttp3.MediaType mediaType) {
//        RequestBody requestBody = RequestBody.create(GSON.toJson(body), mediaType);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//        try {
//            return OK_HTTP_CLIENT.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void registrationPositiveApiTest() {
//        User user = createValidUser();
//        System.out.println(user);
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            System.out.println(response.code());
//            Assert.assertEquals(response.code(), 200);
//        }
//    }
//
//    @Test
//    public void registrationNegativeWrongPasswordApiTest() {
//        User user = createValidUser();
//        user.setPassword("wrong password");
//        System.out.println(user);
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativeWithoutEmailApiTest() {
//        User user = createValidUser();
//        user.setUsername(null);
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativeWithoutPasswordApiTest() {
//        User user = createValidUser();
//        user.setPassword(null);
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativeWithWrongEndpointApiTest() {
//        User user = createValidUser();
//
//        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 401);
//        }
//    }
//
//    @Test
//    public void registrationNegativePasswordIsEmptyApiTest() {
//        User user = createValidUser();
//        user.setPassword("");
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativeDuplicateUserApiTest() {
//        User user = createDuplicateUser();
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 409);
//        }
//    }
//
//    @Test
//    public void registrationNegativeWrongUrlApiTest() {
//        User user = createValidUser();
//
//        try (Response response = postRequest(BASE_URL + "/" + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 403);
//            System.out.println(response.code());
//        }
//    }
//
//    @Test
//    public void registrationNegativeWrongFormatTextApiTest() {
//        User user = createValidUser();
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, TEXT)) {
//            Assert.assertEquals(response.code(), 500);
//        }
//    }
//
//    @Test
//    public void registrationNegativeWrongKeyUserApiTest() {
//        User user = createValidUser();
//
//        Map<String, String> invalidJson = new HashMap<>();
//        invalidJson.put("name", user.getUsername());
//        invalidJson.put("password", user.getPassword());
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, invalidJson, JSON)) {
//            Assert.assertEquals(response.code(), 500);
//            String responseBody = response.body().string();
//            System.out.println(response.code());
//            System.out.println("Status code: " + response.code());
//            System.out.println("Response body: " + responseBody);
//
//        }
//    }
//
//    @Test
//    public void registrationNegativePasswordWithoutUpperCaseApiTest() {
//        User user = new User("user" + System.currentTimeMillis() + "@gmail.com", "parol12!");
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativePasswordWithoutLowerCaseApiTest() {
//        User user = new User("user" + System.currentTimeMillis() + "@gmail.com", "PAROL12!");
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativePasswordWithoutNumberApiTest() {
//        User user = new User("user" + System.currentTimeMillis() + "@gmail.com", "Parolll!");
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativePasswordWithoutSpecSymbolApiTest() {
//        User user = new User("user" + System.currentTimeMillis() + "@gmail.com", "Parol12l");
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativePasswordShortLengthApiTest() {
//        User user = new User("user" + System.currentTimeMillis() + "@gmail.com", "Par12!");
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativeWithoutAtUsernameApiTest() {
//        User user = new User(
//                "loginyoh.com",
//                getProperty("base.properties", "password")
//        );
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativeWithTwoDotsUsernameApiTest() {
//        User user = new User(
//                "loginy..oh.com@gmail.com",
//                getProperty("base.properties", "password")
//        );
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//
//    @Test
//    public void registrationNegativeSubstitutionLoginPasswordApiTest() {
//        User user = new User(
//                getProperty("base.properties", "password"),
//                getProperty("base.properties", "login")
//        );
//
//        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
//            Assert.assertEquals(response.code(), 400);
//        }
//    }
//    @Test
//    public void registrationNegativeWrongBaseUrlApiTest() {
//        User user = positiveUser();
//
//        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
//
//        Request request = new Request.Builder()
//                .url(BASE_URLWRONG + REGISTRATION_URL)
//                .post(requestBody)
//                .build();
//
//        try {
//            Response response = OK_HTTP_CLIENT.newCall(request).execute();
//            String responseBody = response.body().string();
//
//            System.out.println("Status code: " + response.code());
//            System.out.println("Response body: " + responseBody);
//
//            Assert.assertNotEquals(response.code(), 200, "BUG: wrong base URL returned 200");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
////    @Test(expectedExceptions = RuntimeException.class)
////    public void registrationNegativeWrongBaseUrlApiTest() {
////        User user = createDuplicateUser();
////        try (Response response = postRequest(BASE_URLWRONG + REGISTRATION_URL, user, JSON)) {
////            System.out.println(response.code());
////        }
////    }
//}
package api_tests;

import dto.User;
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

    private Response postRequest(String url, Object body, okhttp3.MediaType mediaType) {
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
            Assert.assertEquals(response.code(), 200,
                    "Expected status code 200, but found " + response.code());
        }
    }

    @Test
    public void registrationNegativeWrongPasswordApiTest() {
        User user = createValidUser();
        user.setPassword("wrong password");

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 400,
                    "Expected status code 400, but found " + response.code());
        }
    }

    @Test
    public void registrationNegativeWithoutEmailApiTest() {
        User user = createValidUser();
        user.setUsername(null);

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 400,
                    "Expected status code 400, but found " + response.code());
        }
    }

    @Test
    public void registrationNegativeWithoutPasswordApiTest() {
        User user = createValidUser();
        user.setPassword(null);

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 400,
                    "Expected status code 400, but found " + response.code());
        }
    }

    @Test
    public void registrationNegativeWithWrongEndpointApiTest() {
        User user = createValidUser();

        try (Response response = postRequest(BASE_URL + LOGIN_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 401,
                    "Expected status code 401, but found " + response.code());
        }
    }

    @Test
    public void registrationNegativePasswordIsEmptyApiTest() {
        User user = createValidUser();
        user.setPassword("");

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 400,
                    "Expected status code 400, but found " + response.code());
        }
    }

    @Test
    public void registrationNegativeDuplicateUserApiTest() {
        User user = createDuplicateUser();

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, JSON)) {
            Assert.assertEquals(response.code(), 409,
                    "Expected status code 409, but found " + response.code());
        }
    }

    @Test
    public void registrationNegativeWrongUrlApiTest() {
        User user = createValidUser();

        try (Response response = postRequest(BASE_URL + "/v1/regis", user, JSON)) {
            Assert.assertEquals(response.code(), 403,
                    "Expected status code 404_403!, but found " + response.code());
        }
    }

    @Test
    public void registrationNegativeWrongFormatTextApiTest() {
        User user = createValidUser();

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, user, TEXT)) {
            Assert.assertEquals(response.code(), 500,
                    "Expected status code 500, but found " + response.code());
        }
    }

    @Test
    public void registrationNegativeWrongKeyUserApiTest() throws IOException {
        User user = createValidUser();

        Map<String, String> invalidJson = new HashMap<>();
        invalidJson.put("invalidUsername", user.getUsername());
        invalidJson.put("password", user.getPassword());

        try (Response response = postRequest(BASE_URL + REGISTRATION_URL, invalidJson, JSON)) {
            String responseBody = response.body().string();

            System.out.println("Status code: " + response.code());
            System.out.println("Response body: " + responseBody);

            Assert.assertEquals(response.code(), 500,
                    "Expected status code 500, but found " + response.code());
        }
    }
}