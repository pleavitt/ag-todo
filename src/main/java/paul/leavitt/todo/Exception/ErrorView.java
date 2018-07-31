package paul.leavitt.todo.Exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ErrorView {
    private List<FieldError> details;
    private String name;

    public ErrorView(List<FieldError> details, String name) {
        this.details = details;
        this.name = name;
    }
}
