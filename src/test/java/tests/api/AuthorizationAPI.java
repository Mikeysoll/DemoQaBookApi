package tests.api;

import io.restassured.response.Response;
import tests.models.AuthRequest;
import tests.models.AuthResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.RestAssured.given;


public class AuthorizationAPI {

    public AuthResponse login(String login, String password) {
        AuthRequest authRequest = new AuthRequest(login, password);

        return given()
                .contentType(JSON)
                .body(authRequest)
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);
    }


}