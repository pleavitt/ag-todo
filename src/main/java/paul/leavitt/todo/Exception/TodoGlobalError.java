package paul.leavitt.todo.Exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class TodoGlobalError {
    private String code;

    public TodoGlobalError(String code) {
        this.code = code;
    }
}
