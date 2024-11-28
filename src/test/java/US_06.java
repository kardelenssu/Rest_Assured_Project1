import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class US_06 {

    RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {

        baseURI = "https://api.themoviedb.org/3/";

        requestSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjk2ODFkNTYzOTVhMGM1N2U4MGM5NzNlMTQ5YTdiMSIsIm5iZiI6MTczMjE5NDczNC41NzY4NzU0LCJzdWIiOiI2NzM3ODdlN2Q2M2ZlZDU4MjZjZjVhZjIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.wTDYc46yTOaUm2YlS5LMk2wPU0NDqvKdQdmS1PKerHY")
                .setContentType(ContentType.JSON)
                .build();

    }

    @Test
    public void Task14() {

        given()

                .spec(requestSpec)

                .when()
                .get("search/collection?query=Deadpool%20Collection&include_adult=false&language=en-US&page=1")

                .then()
                .log().body()
                .statusCode(200)
                .body("results[0].name", containsString("Deadpool Collection"))
        ;
    }


    @Test
    public void Task15() {

        String getOriginalName=
        given()
                .spec(requestSpec)

                .when()
                .get("movie/533535?language=en-US")

                .then()
                .log().body()
                .statusCode(200)
                .extract().jsonPath().getString("original_title")
        ;
       Assert.assertEquals(getOriginalName, "Deadpool & Wolverine");
    }
}



