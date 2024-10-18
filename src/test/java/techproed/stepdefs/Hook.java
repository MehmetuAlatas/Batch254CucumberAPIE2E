package techproed.stepdefs;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.specification.RequestSpecification;
import techproed.utilities.Authentication;
import techproed.utilities.ConfigReader;

public class Hook {

    public static RequestSpecification spec;

    @Before("@apie2e")//bu methodu @apie2e notasyonuna sahip olan scenariolar icin özellestirdik, sadece onlardan calisir
    public void setUp() throws Exception {

       spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseUrl"))
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer "+ Authentication.generateToken())
                .build();
    }


}
