package ru.praktikumservices.qascooter.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikumservices.qascooter.models.Courier;

import static io.restassured.RestAssured.given;
import static ru.praktikumservices.qascooter.constants.Configurations.*;

public class CourierSteps{
    @Step
    public ValidatableResponse create(Courier courier){
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_CREATE_PATH)
                .then();
    }
    @Step
    public ValidatableResponse login(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_LOGIN_PATH)
                .then();
    }
    @Step
    public ValidatableResponse delete(Courier courier) {
             return given().log().all()
                .contentType(ContentType.JSON)
                .pathParams("id", courier.getId())
                .when()
                .delete(COURIER_DELETE_PATH)
                .then();
    }
}
