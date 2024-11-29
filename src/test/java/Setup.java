import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;

public class Setup {
    RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {

        baseURI = "https://api.themoviedb.org/3/";

        requestSpec = new RequestSpecBuilder()
                .addHeader("Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjk2ODFkNTYzOTVhMGM1N2U4MGM5NzNlMTQ5YTdiMSIsIm5iZiI6MTczMjE5NDczNC41NzY4NzU0LCJzdWIiOiI2NzM3ODdlN2Q2M2ZlZDU4MjZjZjVhZjIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.wTDYc46yTOaUm2YlS5LMk2wPU0NDqvKdQdmS1PKerHY")
                .setContentType(ContentType.JSON)
                .build();

    }
}
