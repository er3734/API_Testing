package practiceTests;

import baseUrl.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresinPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class P02 extends ReqresBaseUrl {
    //2:  Map ve Pojo Class ile ayrı ayrı yapınız.
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
*/

    @Test
    public void p02_Map() {
        spec.pathParam("first", "users");

        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("name", "morpheus");
        expectedData.put("job", "leader");

        Response response =
                given().spec(spec).
                        contentType(ContentType.JSON).
                        body(expectedData).when().
                        post("/{first}");
        response.prettyPrint();

        Map atualData = response.as(Map.class);
        System.out.println("atualData = " + atualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("name"), atualData.get("name"));
        assertEquals(expectedData.get("job"), atualData.get("job"));
    }

    @Test
    public void p02_Pojo() {

        spec.pathParam("first", "users");

        ReqresinPojo expectedData = new ReqresinPojo("morpheus", "leader");
        System.out.println("expectedData = " + expectedData);

        Response response =
                given().spec(spec).
                        contentType(ContentType.JSON).
                        body(expectedData).when().
                        post("/{first}");
        response.prettyPrint();
        
        ReqresinPojo actualData=response.as(ReqresinPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(),actualData.getJob());

    }
}
