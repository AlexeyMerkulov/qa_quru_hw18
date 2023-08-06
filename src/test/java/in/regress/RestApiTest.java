package in.regress;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RestApiTest {

    @Test
    void successfullGetUserWithId3() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/3")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(3))
                .body("data.email", is("emma.wong@reqres.in"))
                .body("data.first_name", is("Emma"))
                .body("data.last_name", is ("Wong"))
                .body("data.avatar", is("https://reqres.in/img/faces/3-image.jpg"));
    }

    @Test
    void unsuccessfullGetUserWithNonExistedId() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void successfullCreateUser() {
        String userData = "{ \"name\": \"Sergey Petrov\", \"job\": \"teacher\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Sergey Petrov"))
                .body("job", is("teacher"));
    }

    @Test
    void successfullDeleteUser() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void successfullUpdateUserWithId2() {
        String userUpdateData = "{ \"name\": \"John Soprano\", \"job\": \"cleaner\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userUpdateData)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("John Soprano"))
                .body("job", is("cleaner"));
    }
}
