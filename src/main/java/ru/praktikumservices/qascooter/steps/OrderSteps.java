package ru.praktikumservices.qascooter.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikumservices.qascooter.models.Orders;

import static io.restassured.RestAssured.given;
import static ru.praktikumservices.qascooter.constants.Configurations.CREATE_ORDER_PATH;
import static ru.praktikumservices.qascooter.constants.Configurations.GET_ORDERS_LIST;

public class OrderSteps {
    @Step("Create order")
    public ValidatableResponse createOrder(Orders orders) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(orders)
                .when()
                .post(CREATE_ORDER_PATH)
                .then();
    }

    @Step("Get orders")
    public ValidatableResponse getOrders() {
        return given().log().all()
                .when()
                .get(GET_ORDERS_LIST)
                .then();
    }
}
