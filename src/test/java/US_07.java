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

public class US_07 {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void setUp(){

        baseURI="https://api.themoviedb.org/3/";
        requestSpecification=new RequestSpecBuilder()
                .addHeader("Authorization ","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjk2ODFkNTYzOTVhMGM1N2U4MGM5NzNlMTQ5YTdiMSIsIm5iZiI6MTczMjE5NDczNC41NzY4NzU0LCJzdWIiOiI2NzM3ODdlN2Q2M2ZlZDU4MjZjZjVhZjIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.wTDYc46yTOaUm2YlS5LMk2wPU0NDqvKdQdmS1PKerHY")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void Task16(){
        given()
                .spec(requestSpecification)
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
                .spec(requestSpecification)
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
