package api_tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.PropertiesReader.getProperty;

public class AddContact implements BaseApi {
    String token;

    @BeforeClass
    public void login() {
        User user = new User(
                getProperty("base.properties", "login"),
                getProperty("base.properties", "password")
        );

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            Assert.assertNotNull(response.body(), "Response body is null");

            String responseBody = response.body().string();
            System.out.println("Status code: " + response.code());
            System.out.println("Response body: " + responseBody);

            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonElement tokenElement = json.get("token");

            Assert.assertEquals(response.code(), 200, "Login response code is not 200");
            Assert.assertNotNull(tokenElement, "Token was not found in response");

            token = tokenElement.getAsString();
            System.out.println("Token: " + token);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tokenTestPositive() {
        Assert.assertNotNull(token, "Token should not be null");
        System.out.println(token);
    }
}