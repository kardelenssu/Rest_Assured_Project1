
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class US_06 extends Setup{


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



