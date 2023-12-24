package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class APIAssistant {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ValidatableResponse createRequest(String body, int statusCode, String endpoint) {
        return given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .statusCode(statusCode);
    }

}