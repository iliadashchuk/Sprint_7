package ru.praktikumservices.qascooter.order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import jdk.jfr.Description;
import ru.praktikumservices.qascooter.models.Orders;
import ru.praktikumservices.qascooter.steps.OrderSteps;

import java.net.HttpURLConnection;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest extends OrderBaseTest {
    private final Orders orders;
    OrderSteps orderSteps = new OrderSteps();

    public CreateOrderTest (Orders orders){
        this.orders = orders;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {new Orders("Яндекс", "Практикум", "Москва, ул Правды, д 8", "Алексеевская", "88007009329", 5, "2024-06-31", "Faster!", new String[]{"BLACK"})},
                {new Orders("Яндекс", "Практикум", "Москва, ул Правды, д 8", "Алексеевская", "88007009329", 5, "2024-06-31", "Faster!", new String[]{"BLACK, GREY"})},
                {new Orders("Яндекс", "Практикум", "Москва, ул Правды, д 8", "Черкизовская", "88007009329", 1, "2024-06-31", "Faster!", new String[]{})},
                {new Orders("Яндекс", "Практикум", "Москва, ул Правды, д 8", "Черкизовская", "88007009329", 3, "2024-06-31", "Faster!", new String[]{"GREY"})}
        };
    }

    @Test
    @DisplayName("Check status code and body of /orders")
    @Description("Positive scenario for creating order")
    public void createOrderTest(){
        orderSteps.createOrder(orders)
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .assertThat()
                .body("track", notNullValue());
    }
}
