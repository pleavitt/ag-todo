package paul.leavitt.todo;

import org.springframework.web.bind.annotation.*;
import paul.leavitt.todo.Exception.TodoNotFoundException;

import javax.validation.Valid;

@RestController
public class TodoController {

    private TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todo/{id}")
    public Todo getTodoById(@PathVariable Long id) {

        return repository.findById(id)
               .orElseThrow(() -> new TodoNotFoundException("Item", id));
    }

    @PostMapping("/todo")
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        return repository.save(todo);
    }

    @PatchMapping("/todo/{id}")
    public Todo updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todoContents) {

        Todo todo = repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Item", id));
        todo.setText(todoContents.getText());
        todo.setIsCompleted(todoContents.isIsCompleted());

        return repository.save(todo);
    }
}
