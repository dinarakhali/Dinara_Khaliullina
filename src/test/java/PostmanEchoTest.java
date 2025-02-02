import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PostmanEchoTest {
    private String siteURL = "https://postman-echo.com";

    public void testRequest(Response response, String expectedUrl, String expectedBody, String exceptedLength) {
        Map<String, String> headers = response.jsonPath().getMap("headers");
        assertAll(
                () -> assertEquals(200, response.getStatusCode(), "Код НЕ 200"),

                () -> assertTrue(response.jsonPath().getMap("args").isEmpty(), "Args должно быть пустым"),
                () -> assertEquals(expectedBody, response.jsonPath().getString("data"), "Тело ответа НЕ совпадает"),
                () -> assertEquals("postman-echo.com", headers.get("host"), "Host НЕ совпадает"),
                () -> assertEquals(exceptedLength, headers.get("content-length"), "Content-Length НЕ совпадает"),
                () -> assertEquals("*/*", headers.get("accept"), "Accept НЕ совпадает"),
                () -> assertNull(headers.get("cache-control"), "Cache-Control НЕ null"),

                () -> assertEquals(siteURL + expectedUrl, response.jsonPath().getString("url"), "URL НЕ совпадает"),
                () -> assertNull(response.jsonPath().get("json"), "Json должен быть null")
        );
    }

    @Test
    public void getTest() {
        Response response = given()
                .baseUri(siteURL)
                .when()
                .get("/get")
                .then()
                .extract()
                .response();

        testRequest(response, "/get", null, null);
    }

    @Test
    public void postTest() {
        String requestBody = "post test";
        Response response = given()
                .baseUri(siteURL)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .extract()
                .response();

        testRequest(response, "/post", "post test", "9");
    }

    @Test
    public void putTest() {
        String requestBody = "put test";
        Response response = given()
                .baseUri(siteURL)
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .extract()
                .response();

        testRequest(response, "/put", "put test", "8");
    }

    @Test
    public void patchTest() {
        String requestBody = "patch test";
        Response response = given()
                .baseUri(siteURL)
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .extract()
                .response();

        testRequest(response, "/patch", "patch test", "10");
    }

    @Test
    public void deleteTest() {
        String requestBody = "delete test";
        Response response = given()
                .baseUri(siteURL)
                .body(requestBody)
                .when()
                .delete("/delete")
                .then()
                .extract()
                .response();

        testRequest(response, "/delete", "delete test", "11");
    }
}