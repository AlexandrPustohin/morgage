package com.mortgageappl.mortgage;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import com.mortgageappl.mortgage.model.Mortgage;
import com.mortgageappl.mortgage.repository.MortgageRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@SpringBootTest(classes = MortgageApplication.class)
public class RestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private MortgageRepository mortgageRepository;

    @Before
    public void setup() {
        RestAssured.port = port;
    }
    @After
    public void resetDb() {
        mortgageRepository.deleteAll();
        mortgageRepository.flush();
    }

    @Test
    public void whenCreateMortgage() throws Exception {

        Mortgage mortgage = creatMortgage();
        given().log().body()
                .contentType("application/json").body(mortgage)
                .when().post("/mortgage")
                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("customer",  equalTo(mortgage.getCustomer()))
                .and().body("passport", equalTo(mortgage.getPassport()))
                .and().body("address", equalTo(mortgage.getAddress()))
                .and().body("phon", equalTo(mortgage.getPhon()))
                .and().body("summa", equalTo(mortgage.getSumma()))
                .and().body("duration", equalTo(mortgage.getDuration()))
                .and().body("subject", equalTo(mortgage.getSubject()))
                .and().body("supplier", equalTo(mortgage.getSupplier()))
                .and().body("supAddress", equalTo(mortgage.getSupAddress()))
                .and().body("inn", equalTo(mortgage.getInn()));

    }

    @Test
    public void givenWrongINN_whenCreateMortgage() throws Exception {

        Mortgage mortgage = creatMortgage();
        mortgage.setInn("124578545221");
        given().log().body()
                .contentType("application/json").body(mortgage)
                .when().post("/mortgage")
                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("details",  equalTo("MissmachCheckExeption"))
                ;

    }

    @Test
    public void whenGetMorgage() throws Exception {

        Mortgage mortgage = creatMortgage();
        Mortgage saveedMortgage = mortgageRepository.save(mortgage);
        long id = saveedMortgage.getId();

        given().pathParam("id", id)
                .when().get("/mortgage/{id}")
                .then().log().body().statusCode(HttpStatus.OK.value())
                .and().body("id", equalTo((int)id))
                .and().body("customer",  equalTo(mortgage.getCustomer()))
                .and().body("passport", equalTo(mortgage.getPassport()))
                .and().body("address", equalTo(mortgage.getAddress()))
                .and().body("phon", equalTo(mortgage.getPhon()))
                .and().body("summa", equalTo(mortgage.getSumma()))
                .and().body("duration", equalTo(mortgage.getDuration()))
                .and().body("subject", equalTo(mortgage.getSubject()))
                .and().body("supplier", equalTo(mortgage.getSupplier()))
                .and().body("supAddress", equalTo(mortgage.getSupAddress()))
                .and().body("inn", equalTo(mortgage.getInn()));
    }

    @Test
    public void testMortgageList() {
        Mortgage mortgage1 = creatMortgage();
        Mortgage mortgage2 = creatMortgage();
        mortgage2.setCustomer("AnotherCustomer");

        mortgageRepository.save(mortgage1);
        mortgageRepository.save(mortgage2);
        when().get("/mortgage")

                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("get(0).customer", equalTo(mortgage1.getCustomer()))
                .and().body("get(1).customer", equalTo(mortgage2.getCustomer()));
    }

    @Test
    public void givenNoMortgage_whenGetMorgage() {
        given().pathParam("id", 1)
                .when().get("/mortgage/{id}")

                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .and().body("details", equalTo("ResourceNotFoundException"));
    }

    public Mortgage creatMortgage(){
        Mortgage mortgage = new Mortgage();

        mortgage.setCustomer("Иванов Иван Иванович");
        mortgage.setPassport("11 22 456789");
        mortgage.setAddress("г. Вологда ул. Ленина 1 кв. 5");
        mortgage.setPhon("8921654987");
        mortgage.setSumma(5000000);
        mortgage.setDuration(10);
        mortgage.setSubject("Деревенский дом с. Покровское ул. Луговая 5");
        mortgage.setSupplier("Агенстов недвижимости Этажи");
        mortgage.setSupAddress("г. Вологда просп. Победы, 28, Вологда");
        mortgage.setInn("353003514634");
        return mortgage;
    }
}
