package ru.praktikumservices.qascooter.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.praktikumservices.qascooter.constants.ResponseMessages;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.*;

public class CreateCourierTest extends CourierBaseTest{

    @Test
    @DisplayName("Create courier with correct credentials")
    @Description("Positive scenario for /api/v1/courier")
    public void create() {
        courierSteps
                .create(courier)
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .body("ok", is(true));
    }
    @Test
    @DisplayName("Create courier without firstname")
    @Description("Positive scenario for /api/v1/courier")
    public void createCourierWithoutFirstName() {
        courier.setFirstName("");

        courierSteps
                .create(courier)
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .body("ok", is(true));
    }
    @Test
    @DisplayName("Create courier with already existed credentials")
    @Description("Negative scenario for /api/v1/courier")
    public void createExistedCourier() {
        courierSteps
                .create(courier);
        courierSteps
                .create(courier)
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .body("message", equalTo(ResponseMessages.CREATION_CONFLICT));
    }
    @Test
    @DisplayName("Create courier without login data")
    @Description("Negative scenario for /api/v1/courier")
    public void createCourierWithoutLogin() {
        courier.setLogin("");

        courierSteps
                .create(courier)
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo(ResponseMessages.MISSING_DATA_ON_CREATION));
    }
    @Test
    @DisplayName("Create courier without password data")
    @Description("Negative scenario for /api/v1/courier")
    public void createCourierWithoutPassword() {
        courier.setPassword("");

        courierSteps
                .create(courier)
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo(ResponseMessages.MISSING_DATA_ON_CREATION));
    }
}
