import com.sun.net.httpserver.Request;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.baseURI;

public class US_08 extends Setup{


    @Test
    public void Task18(){
        given()

                .spec(requestSpec)

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

                .spec(requestSpec)

                .when()
                .post("list/8297247/add_item?session_id=123456")

                .then()
                .log().body()
                .statusCode(401)
                .body("status_message", equalTo("Authentication failed: You do not have permissions to access the service."));

    }









}
