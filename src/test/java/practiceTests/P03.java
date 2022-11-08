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

public class P03 extends ReqresBaseUrl {
    //3: Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.

    /*
            Given
                1) https://reqres.in/api/users/2
                2) {
                    "name": "morpheus",
                    "job": "zion president"
                    }
            When
                I send Put Request to the Url
            Then
                Status code is 200
                And response body should be like {
                                                    "name": "morpheus",
                                                    "job": "zion president",
                                                    "updatedAt": "2022-10-02T11:35:05.693Z"
                                                }
    */
    @Test
    public void p03_Map() {

        spec.pathParams("first", "users", "second", 2);

        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("name", "morpheus");
        expectedData.put("job", "zion president");

        Response response =
                given().spec(spec).
                        contentType(ContentType.JSON).
                        body(expectedData).
                        when().put("/{first}/{second}");
        response.prettyPrint();

        Map actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));
    }

    @Test
    public void p03_Pojo() {
        spec.pathParams("first", "users", "second", 2);

        ReqresinPojo expectedData =
                new ReqresinPojo("morpheus", "zion president");

        Response response =
                given().spec(spec).
                        contentType(ContentType.JSON).
                        body(expectedData).
                        when().put("/{first}/{second}");
        response.prettyPrint();

        ReqresinPojo actualData=response.as(ReqresinPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(),actualData.getJob());
    }
}
