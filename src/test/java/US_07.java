import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class US_07 extends Setup{


    @Test
    public void Task16(){
        given()
                .spec(requestSpec)
                .when()
                .get("search/keyword?query=lost%20girl&page=1")

                .then()
                .log().body()
                .statusCode(200)
                .body("results[0].name",containsString("lost girl"))

        ;

    }

    @Test
    public void Task17(){
        Map<String,Double> map=new HashMap<>();
        map.put("value",8.50);
        String messageControl=
        given()
                .spec(requestSpec)
                .body(map)

                .when()
                .post("movie/54051/rating")

                .then()
                .log().body()
                .statusCode(201)
                .extract().body().jsonPath().get("status_message")
        ;
        Assert.assertTrue(messageControl.contains("successfully"),"Rating güncelleme başarısız");
    }


}
