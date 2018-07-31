package paul.leavitt.todo;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

@Validated
@RestController
public class BracketController {

    @GetMapping("/tasks/validateBrackets")
    public Brackets validateBrackets(@Size(min = 1, max = 50, message = "Must be between 1 and 50 chars long") @RequestParam String input) {

        return new Brackets(input);
    }

}
