package paul.leavitt.todo.Exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class GlobalError {
    private String code;

    public GlobalError(String code) {
        this.code = code;
    }
}
