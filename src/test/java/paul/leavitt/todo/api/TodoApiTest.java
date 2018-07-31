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
import static org.assertj.core.api.Assertions.assertThat;

//Testing the Creation, retrieval and update of a Todo
public class TodoApiTest {

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
    public void createTodoAndVerifyCreation() {

        Todo newTodo = new Todo("Catch Bus");
        Todo doNotTodo = new Todo("Catch Train");

        Todo responseTodo = given()
                .spec(setup)
                .body(newTodo)
                .when()
                .post("todo")
                .then()
                .statusCode(200)
                .extract().as(Todo.class);

        assertThat(responseTodo).isEqualToIgnoringGivenFields(newTodo, "id", "createdAt");

        assertThat(responseTodo.getText()).isNotEqualTo(doNotTodo.getText());
    }

    @Test
    public void createEmptyTodo() {

        Todo emptyTodo = new Todo("");

        given()
                .spec(setup)
                .body(emptyTodo)
                .when()
                .post("todo")
                .then()
                .statusCode(400);
    }

    @Test
    public void createHugeTodo() {

        Todo hugeTodo = new Todo("Do this, and then do this, and then do that, and then do this");

        given()
                .spec(setup)
                .body(hugeTodo)
                .when()
                .post("todo")
                .then()
                .statusCode(400);
    }


    @Test
    public void retrieveExistingTodo() {

        given()
                .spec(setup)
                .when()
                .get("todo/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void retrieveNonExistingTodo() {

        given()
                .spec(setup)
                .when()
                .get("todo/9999")
                .then()
                .statusCode(404);
    }

    @Test
    public void updateExistingTodo() {

        Todo updateTodo = given()
                .spec(setup)
                .when()
                .get("todo/1")
                .then()
                .statusCode(200)
                .extract().as(Todo.class);

        updateTodo.setText("Put shoes on, on the correct foot");

        Todo responseTodo = given()
                .spec(setup)
                .body(updateTodo)
                .when()
                .patch("todo/" + updateTodo.getId())
                .then()
                .statusCode(200)
                .extract().as(Todo.class);

        assertThat(responseTodo).isEqualToIgnoringGivenFields(updateTodo, "id", "createdAt");

        updateTodo.setText("");

        given()
                .spec(setup)
                .body(updateTodo)
                .when()
                .patch("todo/" + updateTodo.getId())
                .then()
                .statusCode(400);

        updateTodo.setText("Eat some icecream and some chocolate topping and chocolate freckles and malteesers");

        given()
                .spec(setup)
                .body(updateTodo)
                .when()
                .patch("todo/" + updateTodo.getId())
                .then()
                .statusCode(400);

        updateTodo.setId(7895);
        updateTodo.setText("Call mum");

        given()
                .spec(setup)
                .body(updateTodo)
                .when()
                .patch("todo/" + updateTodo.getId())
                .then()
                .statusCode(404);

    }
}
