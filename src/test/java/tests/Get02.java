package tests;

import baseUrl.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get02 extends ReqresBaseUrl {
      /*
   Given
       https://reqres.in/api/users/23
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Server is "cloudflare"
   And
       Response body should be empty
*/

    @Test
    public void test01() {
        spec.pathParams("first", "users", "second", 23);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        response.then().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found").
                header("Server", equalTo("cloudflare"));
        assertEquals(2, response.body().asString().replaceAll("\\s", "").length());
    }
}
