package practiceTests;

import baseUrl.AutoExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class P01 extends AutoExerciseBaseUrl {
/*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/

    @Test
    public void p01() {
        spec.pathParam("first","brandsList");

        Response response=given().spec(spec).when().get("/{first}");

        JsonPath jsonPath=response.jsonPath();
        jsonPath.prettyPrint();

        response.then().assertThat().
                statusCode(200).
                contentType("text/html; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");

        List<String>hmList=jsonPath.getList("brands.findAll{it.brand=='H&M'}.brand");
        System.out.println("hmList = " + hmList);
        List<String>poloList=jsonPath.getList("brands.findAll{it.brand=='Polo'}.brand");
        System.out.println("poloList = " + poloList);

        assertNotEquals(poloList.size(),hmList.size());
    }
}
