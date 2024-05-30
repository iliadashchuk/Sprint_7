package ru.praktikumservices.qascooter.courier;

import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Test;
import ru.praktikumservices.qascooter.constants.ResponseMessages;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest extends  CourierBaseTest{

    @Test
    @DisplayName("Login courier")
    @Description("Positive scenario for /api/v1/courier/login")
    public void loginCourier(){
        courierSteps
                .create(courier);
        courierSteps
                .login(courier)
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("id", notNullValue());
    }
    @Test
    @DisplayName("Login courier without login")
    @Description("Negative scenario for /api/v1/courier/login")
    public void loginCourierWithoutLogin() {
        courierSteps
                .create(courier);

        courier.setLogin("");

        courierSteps
                .login(courier)
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo(ResponseMessages.MISSING_DATA_ON_LOGIN));
    }
    @Test
    @DisplayName("Login courier without password")
    @Description("Negative scenario for /api/v1/courier/login")
    public void loginCourierWithoutPassword() {
        courierSteps
                .create(courier);

        courier.setPassword("");

        courierSteps
                .login(courier)
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo(ResponseMessages.MISSING_DATA_ON_LOGIN));
    }
    @Test
    @DisplayName("Login with unreal credentials")
    @Description("Negative scenario for /api/v1/courier/login")
    public void loginUnrealCourier(){
        courierSteps
                .login(courier)
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body("message", equalTo(ResponseMessages.ACCOUNT_NOT_FOUND));
    }
    @Test
    @DisplayName("Login with wrong login")
    @Description("Negative scenario for /api/v1/courier/login")
    public void loginCourierWithWrongLogin(){
        courier.setLogin("Wrong_Login_1");

        courierSteps
                .login(courier)
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body("message", equalTo(ResponseMessages.ACCOUNT_NOT_FOUND));
    }
    @Test
    @DisplayName("Login with wrong password")
    @Description("Negative scenario for /api/v1/courier/login")
    public void loginCourierWithWrongPassword(){
            courier.setLogin("Wrong_Password_1");

            courierSteps
                    .login(courier)
                    .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .body("message", equalTo(ResponseMessages.ACCOUNT_NOT_FOUND));
        }
}
