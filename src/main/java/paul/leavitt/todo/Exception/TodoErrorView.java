package paul.leavitt.todo.Exception;

import lombok.Getter;

import java.util.List;

@Getter
public class TodoErrorView {
    private List<TodoFieldError> details;
    private String name;

    public TodoErrorView(List<TodoFieldError> details, String name) {
        this.details = details;
        this.name = name;
    }
}
