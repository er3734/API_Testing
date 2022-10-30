package Tests;

import baseUrl.RestfulHerOkuBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get04 extends RestfulHerOkuBaseUrl {
    /*
     Given
         https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
     When
         User sends get request to the URL
     Then
         Status code is 200
     And
         Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"
  */
    @Test
    public void test() {
        spec.pathParams("first", "booking").
                queryParams("firstname", "Almedin", "lastname", "Alikadic");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().statusCode(200);
        assert response.asString().contains("bookingid");
    }
}
