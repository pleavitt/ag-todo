package paul.leavitt.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BracketController {

    @GetMapping("/tasks/validateBrackets")
    public Brackets validateBrackets(@RequestParam String input) {

        return new Brackets(input);
    }

}
