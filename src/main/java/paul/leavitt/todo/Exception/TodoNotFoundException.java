package paul.leavitt.todo.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends RuntimeException {
    private String todoName;
    private Object fieldValue;

    public TodoNotFoundException(String todoName, Object fieldValue) {
        super(String.format("%s with %s not found", todoName, fieldValue));
        this.todoName = todoName;
        this.fieldValue = fieldValue;
    }
}
