package com.mortgageappl.mortgage;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import com.mortgageappl.mortgage.model.Morgage;
import com.mortgageappl.mortgage.repository.MorgageRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@SpringBootTest(classes = MortgageApplication.class)
public class RestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private MorgageRepository morgageRepository;

    @Before
    public void setup() {

        RestAssured.port = port;
    }
    @Test
    public void whenCreateMorgage() throws Exception {
        // Given
        Morgage morgage = creatMorgage();
        given().log().body()
                .contentType("application/json").body(morgage)
                .when().post("/morgage")
                .then().log().body()
                .statusCode(HttpStatus.OK.value());

    }
    @Test
    public void whenGetMorgage() throws Exception {
        // Given
        Morgage morgage = creatMorgage();
        Morgage saveedMorgage = morgageRepository.save(morgage);
        long id = saveedMorgage.getId();

        given().pathParam("id", id)
                .when().get("/morgage/{id}")
                .then().log().body().statusCode(HttpStatus.OK.value())
                .and().body("id", equalTo(1))
                .and().body("castomer", equalTo("Пустохин Александр Евреньевич"));

    }
    public Morgage creatMorgage(){
        Morgage morgage = new Morgage();

        morgage.setCastomer("Пустохин Александр Евреньевич");
        morgage.setPassport("19 02 730670");
        morgage.setAddress("г. Вологда ул. Карла-Маркса 113 б кв. 45");
        morgage.setPhon("89211400904");
        morgage.setSumma(5000000);
        morgage.setDuration(10);
        morgage.setSubject("Деревенский дом с. Покровское ул. Луговая 5");
        morgage.setSupplier("Агенстов недвижимости Этажи");
        morgage.setSupAddress("г. Вологда просп. Победы, 28, Вологда");
        morgage.setInn("353003514634");
        return morgage;
    }
}
