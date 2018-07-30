package paul.leavitt.todo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BracketController {

    @PostMapping("/tasks/validateBrackets")
    public Brackets validateBrackets(@Valid @RequestBody Brackets brackets) {

        return new Brackets(brackets.getInput());
    }

}
