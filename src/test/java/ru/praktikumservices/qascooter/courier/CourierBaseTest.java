package ru.praktikumservices.qascooter.courier;

import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import ru.praktikumservices.qascooter.models.Courier;
import ru.praktikumservices.qascooter.steps.CourierSteps;

public class CourierBaseTest {
    protected final CourierSteps courierSteps = new CourierSteps();
    Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";

        // Create random credentials
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(5, 10));
        courier.setPassword( RandomStringUtils.randomAlphabetic(5, 10));
        courier.setFirstName( RandomStringUtils.randomAlphabetic(5, 10));
    }

    @After
    public void delete() {
        Integer id = courierSteps.login(courier)
                .extract()
                .body()
                .path("id");
        courier.setId(id);
        if(id != null){
            courierSteps.delete(courier);
        }
    }
}
