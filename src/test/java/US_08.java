import com.sun.net.httpserver.Request;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.baseURI;

public class US_08 {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void setUp(){

        baseURI = "https://api.themoviedb.org/3/";
        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGYzYmQ2M2FmMjlkODRmNTViOGM1NjJjNWUyNjBiMyIsIm5iZiI6MTczMTg0NzcxNy44NjU1ODk2LCJzdWIiOiI2NzM5ZTRkYTU4NTlmOTgxZWVkZmJkYjYiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.KvXn6NQbaSf8QjnLeNF1kPhV150KQgFaPxqV801Y-94")
                .build();
    }

    @Test
    public void Task18(){
        given()

                .spec(requestSpecification)

                .when()
                .delete("movie/558449/rating")

                .then()
                .log().body()
                .statusCode(200)
                .body("status_message",equalTo("The item/record was deleted successfully."));
    }

    @Test
    public void Task19(){
        given()

                .spec(requestSpecification)

                .when()
                .post("list/8297247/add_item?session_id=123456")

                .then()
                .log().body()
                .statusCode(401)
                .body("status_message", equalTo("Authentication failed: You do not have permissions to access the service."));

    }









}
