package Tests;

import baseUrl.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Get06 extends ReqresBaseUrl {
    /*
Given
      https://reqres.in/api/unknown/
When
    I send GET Request to the URL
Then

    1)Status code is 200
    2)Print all pantone_values
    3)Print all ids greater than 3 on the console
      Assert that there are 3 ids greater than 3
    4)Print all names whose ids are less than 3 on the console
      Assert that the number of names whose ids are less than 3 is 2
*/
    @Test
    public void test() {
        spec.pathParam("first", "unknown");
        Response response = given().spec(spec).when().get("/{first}");

        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        jsonPath.getList("data.pantone_value").forEach(System.out::println);

        List<Integer> idList = jsonPath.getList("data.findAll{it.id>3}.id");
        idList.forEach(System.out::println);
        assert idList.size() == 3;

        List<String> nameList = jsonPath.getList("data.findAll{it.id<3}.name");
        nameList.forEach(System.out::println);
        assert nameList.size() == 2;
    }
}

