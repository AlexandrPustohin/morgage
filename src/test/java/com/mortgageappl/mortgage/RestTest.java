package com.mortgageappl.mortgage;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import com.mortgageappl.mortgage.model.Morgage;
import com.mortgageappl.mortgage.repository.MorgageRepository;

import org.junit.After;
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
    @After
    public void resetDb() {
        morgageRepository.deleteAll();
        morgageRepository.flush();
    }

    @Test
    public void whenCreateMorgage() throws Exception {

        Morgage morgage = creatMorgage();
        given().log().body()
                .contentType("application/json").body(morgage)
                .when().post("/morgage")
                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("castomer",  equalTo(morgage.getCastomer()))
                .and().body("passport", equalTo(morgage.getPassport()))
                .and().body("address", equalTo(morgage.getAddress()))
                .and().body("phon", equalTo(morgage.getPhon()))
                .and().body("summa", equalTo(morgage.getSumma()))
                .and().body("duration", equalTo(morgage.getDuration()))
                .and().body("subject", equalTo(morgage.getSubject()))
                .and().body("supplier", equalTo(morgage.getSupplier()))
                .and().body("supAddress", equalTo(morgage.getSupAddress()))
                .and().body("inn", equalTo(morgage.getInn()));

    }

    @Test
    public void givenWrongINN_whenCreateMorgage() throws Exception {

        Morgage morgage = creatMorgage();
        morgage.setInn("124578545221");
        given().log().body()
                .contentType("application/json").body(morgage)
                .when().post("/morgage")
                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("details",  equalTo("MissmachCheckExeption"))
                ;

    }

    @Test
    public void whenGetMorgage() throws Exception {

        Morgage morgage = creatMorgage();
        Morgage saveedMorgage = morgageRepository.save(morgage);
        long id = saveedMorgage.getId();

        given().pathParam("id", id)
                .when().get("/morgage/{id}")
                .then().log().body().statusCode(HttpStatus.OK.value())
                .and().body("id", equalTo((int)id))
                .and().body("castomer",  equalTo(morgage.getCastomer()))
                .and().body("passport", equalTo(morgage.getPassport()))
                .and().body("address", equalTo(morgage.getAddress()))
                .and().body("phon", equalTo(morgage.getPhon()))
                .and().body("summa", equalTo(morgage.getSumma()))
                .and().body("duration", equalTo(morgage.getDuration()))
                .and().body("subject", equalTo(morgage.getSubject()))
                .and().body("supplier", equalTo(morgage.getSupplier()))
                .and().body("supAddress", equalTo(morgage.getSupAddress()))
                .and().body("inn", equalTo(morgage.getInn()));
    }

    @Test
    public void testMorgageList() {
        Morgage morgage1 = creatMorgage();
        Morgage morgage2 = creatMorgage();
        morgage2.setCastomer("AnotherCastomer");

        morgageRepository.save(morgage1);
        morgageRepository.save(morgage2);
        when().get("/morgage")

                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("get(0).castomer", equalTo(morgage1.getCastomer()))
                .and().body("get(1).castomer", equalTo(morgage2.getCastomer()));
    }

    @Test
    public void givenNoMorgage_whenGetMorgage() {
        given().pathParam("id", 1)
                .when().get("/morgage/{id}")

                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("details", equalTo("ResourceNotFoundException"));
    }

    public Morgage creatMorgage(){
        Morgage morgage = new Morgage();

        morgage.setCastomer("Иванов Иван Иванович");
        morgage.setPassport("11 22 456789");
        morgage.setAddress("г. Вологда ул. Ленина 1 кв. 5");
        morgage.setPhon("8921654987");
        morgage.setSumma(5000000);
        morgage.setDuration(10);
        morgage.setSubject("Деревенский дом с. Покровское ул. Луговая 5");
        morgage.setSupplier("Агенстов недвижимости Этажи");
        morgage.setSupAddress("г. Вологда просп. Победы, 28, Вологда");
        morgage.setInn("353003514634");
        return morgage;
    }
}
