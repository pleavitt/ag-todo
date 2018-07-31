package paul.leavitt.todo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(String todoName, Object fieldValue) {
        super(String.format("%s with %s not found", todoName, fieldValue));
        String todoName1 = todoName;
        Object fieldValue1 = fieldValue;
    }
}
