package ru.praktikumservices.qascooter.order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import jdk.jfr.Description;
import ru.praktikumservices.qascooter.steps.OrderSteps;

import java.net.HttpURLConnection;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrderListTest extends OrderBaseTest{
    OrderSteps orderSteps = new OrderSteps();

    @Test
    @DisplayName("Check status code and body of /orders")
    @Description("Positive scenario")
    public void getOrders(){
        orderSteps.getOrders()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders", notNullValue());
    }
}
