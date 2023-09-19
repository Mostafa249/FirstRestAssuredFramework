package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//UserEndpoints.java
//Created for perform Create,Read,Update,Delete requests for user api.
public class UserEndpoints {
    public static Response createUser(User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.userPostUrl);
        return response;
    }

    public static Response readUser(String userName) {
        Response response = given()
                .pathParam("username", userName)
                .when()
                .get(Routes.userGetUrl);
        return response;
    }

    public static  Response updateUser(String userName, User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(Routes.userUpdateUrl);
        return response;
    }

    public static Response deleteUser(String userName) {
        Response response = given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.userDeleteUrl);
        return response;
    }
};
