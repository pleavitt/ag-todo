package paul.leavitt.todo;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
public class BracketController {

    @GetMapping("/tasks/validateBrackets/{params}")
    public Brackets validateBrackets(@PathVariable(value = "params") String params) {

        return new Brackets(params);
    }

}
