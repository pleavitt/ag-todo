package paul.leavitt.todo.Exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoFieldError {
    private String location;
    private String param;
    private String msg;
    private Object value;

    public TodoFieldError(String param, String msg, Object value) {
        this.location = "params";
        this.param = param;
        this.msg = msg;
        this.value = value;
    }

    public TodoFieldError(String msg) {
        this.msg = msg;
    }
}
