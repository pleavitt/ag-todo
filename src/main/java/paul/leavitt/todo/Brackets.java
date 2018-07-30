package paul.leavitt.todo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Brackets implements Serializable {

    @Size(min = 1, max = 50)
    @NotBlank
    private String input;

    private boolean balanced;

    public Brackets(String input){
        this.input = input;
        this.balanced = isBalanced(this.input);
    }

    private boolean isBalanced(String str){
        if (str.length() == 0) {
            return true;
        }
        if (str.contains("()")) {
            return isBalanced(str.replaceFirst("\\(\\)", ""));
        }

        if (str.contains("[]")) {
            return isBalanced(str.replaceFirst("\\[\\]", ""));
        }

        if (str.contains("<>")) {
            return isBalanced(str.replaceFirst("\\<\\>", ""));
        }

        if (str.contains("{}")) {
            return isBalanced(str.replaceFirst("\\{\\}", ""));
        } else {
            return false;
        }
    }
}
