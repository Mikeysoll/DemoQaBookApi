package tests.api;

import tests.models.AddBookRequest;
import tests.models.AddBookResponse;

import static io.restassured.RestAssured.given;

public class BasketAPI {

    public AddBookResponse addBook(String userId, String token, String isbn) {
        AddBookRequest request = new AddBookRequest(userId, isbn);

        return given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201)
                .extract().as(AddBookResponse.class);
    }

    public void clearBasket(String userId, String token){

        given()
                .headers("Authorization", "Bearer " + token)
                .contentType("application/json")
                .when()
                .delete("/BookStore/v1/Books?UserId=" + userId)
                .then()
                .statusCode(204);
    }
}