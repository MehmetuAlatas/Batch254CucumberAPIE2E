package techproed.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateUserStepDef {
   private RequestSpecification spec;
   private Map<String, String> payload;
   private Response response;

    @Given("base URL {string} ve path parametresi {string} kullanilir")
    public void baseURLVePathParametresiKullanilir(String baseUrl, String path) {
        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .build();
    }

    @And("name {string} ve job {string} ile payload olusturulur")
    public void nameVeJobIlePayloadOlusturulur(String name, String job) {
        //1.yol
        payload = new HashMap<>();
        payload.put("name", name);
        payload.put("job", job);
        //2.yol
        // MeselaBuBirPojaClasioLSUN pojo = new MeselaBuBirPojaClasioLSUN(name,job);
    }

    @When("post request gönderilir ve response alinir")
    public void postRequestGönderilirVeResponseAlinir() {
        response = given(spec).body(payload).when().post();
    }

    @Then("status code {int} olmalidir")
    public void statusCodeOlmalidir(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.statusCode());
    }

    @And("response content type {string} olmalidir")
    public void responseContentTypeOlmalidir(String expectedContentType) {
        Assert.assertEquals(expectedContentType, response.getContentType());
    }

    @And("response name {string} ve job {string} olmalidir")
    public void responseNameVeJobOlmalidir(String expectedName, String expectedJob) {
        String actualName = response.jsonPath().getString("name"); //==>ali
        String actualJob = response.jsonPath().getString("job"); //==>qa
        Assert.assertEquals(expectedName,actualName);
        Assert.assertEquals(expectedJob,actualJob);
    }
}
