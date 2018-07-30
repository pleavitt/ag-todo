package paul.leavitt.todo.api;


import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import paul.leavitt.todo.Todo;

import static com.jayway.restassured.RestAssured.given;

public class TodoControllerTest {

    private static RequestSpecification spec;

    @BeforeClass
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }
    @Test
    public void useSpec(){

        Todo newTodo = new Todo("Catch Bus");

        given()
                .spec(spec)
                .param("limit", 20)
                .when()
                .post("todo")
                .then()
                .statusCode(200);
    }
}
