package paul.leavitt.todo.api;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import paul.leavitt.todo.Brackets;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidateBracketsApiTest {

    private static RequestSpecification setup;

    @BeforeClass
    public static void setup() {
        setup = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void validBrackets() {

        Brackets result = given()
                .spec(setup)
                .parameters("input", "()()")
                .when()
                .get("tasks/validateBrackets")
                .then()
                .statusCode(200)
                .extract().as(Brackets.class);

        assertThat(result.isBalanced()).isEqualTo(true);

        result = given()
                .spec(setup)
                .parameters("input", "({<[]>})")
                .when()
                .get("tasks/validateBrackets")
                .then()
                .statusCode(200)
                .extract().as(Brackets.class);

        assertThat(result.isBalanced()).isEqualTo(true);

        result = given()
                .spec(setup)
                .parameters("input", "()<>{}[]wqeoijfeowf()()()()()(){}{}{}[]{}")
                .when()
                .get("tasks/validateBrackets")
                .then()
                .statusCode(200)
                .extract().as(Brackets.class);

        assertThat(result.isBalanced()).isEqualTo(true);
    }

    @Test
    public void invalidBrackets() {

        Brackets result = given()
                .spec(setup)
                .parameters("input", "([)]")
                .when()
                .get("tasks/validateBrackets")
                .then()
                .statusCode(200)
                .extract().as(Brackets.class);

        assertThat(result.isBalanced()).isEqualTo(false);

        result = given()
                .spec(setup)
                .parameters("input", "({<[]>)")
                .when()
                .get("tasks/validateBrackets")
                .then()
                .statusCode(200)
                .extract().as(Brackets.class);

        assertThat(result.isBalanced()).isEqualTo(false);

        result = given()
                .spec(setup)
                .parameters("input", "()<>{}[]wqeoijfeowf()()()(()(){}{}{}[]{}")
                .when()
                .get("tasks/validateBrackets")
                .then()
                .statusCode(200)
                .extract().as(Brackets.class);

        assertThat(result.isBalanced()).isEqualTo(false);
    }

    @Test
    public void invalidParameters() {


        given()
                .spec(setup)
                .parameters("input", "")
                .when()
                .get("tasks/validateBrackets")
                .then()
                .statusCode(400);

        given()
                .spec(setup)
                .parameters("input", "{}()}{)((()()()()){}{}{<>><><}[}[]{}[[]{][{}{]]{]{}[}[}[}[}[][}[}{[)(<>S")
                .when()
                .get("tasks/validateBrackets")
                .then()
                .statusCode(400);

    }
}
