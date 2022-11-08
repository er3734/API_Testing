package practiceTests;

import baseUrl.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresinNamePojo;
import pojos.ReqresinPojo;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class P04 extends ReqresBaseUrl {
    //4: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test
    public void p04_Map() {
        spec.pathParams("first", "users", "second", 2);

        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("name", "neo");

        Response response =
                given().spec(spec).
                        contentType(ContentType.JSON).
                        body(expectedData).
                        when().patch("/{first}/{second}");
        response.prettyPrint();

        Map actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
    }

    @Test
    public void p04_Pojo() {
        spec.pathParams("first", "users", "second", 2);

        ReqresinNamePojo expectedData = new ReqresinNamePojo("neo");
        System.out.println("expectedData = " + expectedData);

        Response response =
                given().spec(spec).
                        contentType(ContentType.JSON).
                        body(expectedData).
                        when().patch("/{first}/{second}");
        response.prettyPrint();

        ReqresinNamePojo actualData=ObjectMapperUtils.convertJsonJava(response.asString(),ReqresinNamePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getName(),actualData.getName());

    }
}
