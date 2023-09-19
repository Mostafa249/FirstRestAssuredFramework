package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class StoreEndpoints {
    public static <Store> Response createOrder(Store payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.storePostUrl);
        return response;
    }

    public static Response readOrder(String orderId) {
        Response response = given()
                .pathParam("orderId", orderId)
                .when()
                .get(Routes.storeGetUrl);
        return response;
    }

    public static Response deleteOrder(String orderId) {
        Response response = given()
                .pathParam("orderId", orderId)
                .when()
                .delete(Routes.storeDeleteUrl);
        return response;
    }
}
