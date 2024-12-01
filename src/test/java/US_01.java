import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class US_01 extends Setup {
    Faker faker = new Faker();
    int movieId = 0;
    int accountId = 0;
    String requestToken = "";

    @Test
    public void setRequestToken(){
        requestToken =
                given()
                        .spec(requestSpec)
                        .body("")

                        .when()
                        .get("/authentication/token/new")

                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().path("request_token")
        ;

    }

    @Test(dependsOnMethods = "setRequestToken")
    public void Login(){
        Map<String,String> loginToken=new HashMap<>();
        loginToken.put("username","team02test");
        loginToken.put("password","123456789");
        loginToken.put("request_token",requestToken);
                given()
                        .spec(requestSpec)
                        .body(loginToken)

                        .when()
                        .post("/authentication/token/validate_with_login")

                        .then()
                        .log().body()
                        .statusCode(200)
                        .body("request_token",equalTo(requestToken))
        ;

    }

    @Test
    public void invalidLogin(){
        Map<String,String> loginToken=new HashMap<>();
        loginToken.put("username","team02test");
        loginToken.put("password","123456789");
        loginToken.put("request_token","invalidToken");
        given()
                .spec(requestSpec)
                .body(loginToken)

                .when()
                .post("/authentication/token/validate_with_login")

                .then()
                .log().body()
                .statusCode(401)
                .body("status_message",containsString("Invalid request token"))
        ;
    }


    @Test
    public void getAccountDetails() {
        accountId =
                given()
                        .spec(requestSpec)
                        .body("")

                        .when()
                        .get("/account")

                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().path("id")
        ;
    }

    @Test
    public void setMovieId() {
        movieId =
                given()
                        .spec(requestSpec)
                        .body("")

                        .when()
                        .get("/movie/top_rated")

                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().path("results[" + faker.number().numberBetween(0, 20) + "].id")
        ;
    }

    @Test(dependsOnMethods = {"setMovieId","getAccountDetails"})
    public void addFavorite(){
        Map<String,String> favorite=new HashMap<>();
        favorite.put("media_type","movie");
        favorite.put("media_id",String.valueOf(movieId));
        favorite.put("favorite","true");
        given()
                .spec(requestSpec)
                .body(favorite)

                .when()
                .post("/account/"+accountId+"/favorite")

                .then()
                .log().body()
                .statusCode(201)
                .body("status_message", containsStringIgnoringCase("success"))
        ;


    }

    @Test(dependsOnMethods = {"setMovieId","getAccountDetails"})
    public void addFavoriteNegative(){
        Map<String,String> favorite=new HashMap<>();
        favorite.put("media_type","movie");
        favorite.put("media_id","invalidMovieId");
        favorite.put("favorite","true");
        given()
                .spec(requestSpec)
                .body(favorite)

                .when()
                .post("/account/"+accountId+"/favorite")

                .then()
                .log().body()
                .statusCode(404)
                .body("status_message",containsString("Invalid id"))
        ;


    }

    @Test(dependsOnMethods = {"setMovieId","getAccountDetails"})
    public void addWatchList(){
        Map<String,String> watchList=new HashMap<>();
        watchList.put("media_type","movie");
        watchList.put("media_id",String.valueOf(movieId));
        watchList.put("watchlist","true");
        given()
                .spec(requestSpec)
                .body(watchList)

                .when()
                .post("/account/"+accountId+"/watchlist")

                .then()
                .log().body()
                .statusCode(201)
                .body("status_message", containsStringIgnoringCase("success"))
        ;

    }

    @Test(dependsOnMethods = {"setMovieId","getAccountDetails"})
    public void addWatchListNegative(){
        Map<String,String> watchList=new HashMap<>();
        watchList.put("media_type","movie");
        watchList.put("media_id","invalidMovieId");
        watchList.put("watchlist","true");
        given()
                .spec(requestSpec)
                .body(watchList)

                .when()
                .post("/account/"+accountId+"/watchlist")

                .then()
                .log().body()
                .statusCode(404)
                .body("status_message",containsString("Invalid id"))
        ;

    }
}
