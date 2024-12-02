import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class US_04 extends Setup {

    @Test
    public void TC10_GetNowPlayingMovies() {

        given()
                .spec(requestSpec)

                .when()
                .get("movie/now_playing")

                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    @Test
    public void TC11_GetPopularMovies() {

        given()
                .spec(requestSpec)

                .when()
                .get("movie/popular")

                .then()
                .log().body()
                .statusCode(200)
        ;
    }
}

