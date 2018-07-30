package paul.leavitt.todo;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
public class BracketController {

    @GetMapping("/tasks/validateBrackets")
    public Brackets validateBrackets(@RequestParam(value = "input") String input) {

        return new Brackets(input);
    }

}
