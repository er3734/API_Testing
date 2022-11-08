package practiceTests;

import baseUrl.PetstoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PetstorePostPojo;

import static io.restassured.RestAssured.given;

public class P05 extends PetstoreBaseUrl {
/*
    https://petstore.swagger.io/ documantation adresini kullanarak 'user'
    oluşturan ve oluşsan bu user'ı silen bir otomasyon kodu yazınız.
    */

    @Test
    public void p05_Post() {
        //https://petstore.swagger.io/v2/user
        spec.pathParams("first", "user" );

        PetstorePostPojo expectedData =
                new PetstorePostPojo("er23", "erman", "yıldız", "e@gmail.com", "12345", 2);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

    }

    @Test
    public void p05_Delete() {
        spec.pathParams("first", "user","second","er23" );

        Response response=given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();
    }
}
