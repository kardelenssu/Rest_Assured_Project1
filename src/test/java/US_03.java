import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class US_03 extends Setup {

    @Test
    public void TC08_GetWatchlistMovies() {

        given()
                .spec(requestSpec)
                .pathParam("account_id", "21632548")

                .when()
                .get("account/{account_id}/watchlist/movies")

                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    @Test
    public void TC09_GetMovieGenres() {

        given()
                .spec(requestSpec)

                .when()
                .get("genre/movie/list")

                .then()
                .log().body()
                .statusCode(200)
        ;
    }
}
