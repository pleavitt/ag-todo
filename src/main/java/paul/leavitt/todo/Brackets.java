package paul.leavitt.todo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Brackets implements Serializable {

    @Size(min = 1, max = 50)
    private String input;

    private boolean isBalanced;

    public Brackets(String input) {
        this.input = input;

        this.isBalanced = isIsBalanced(input);
    }

    private boolean isIsBalanced(String str) {
        str = str.replaceAll("[^()\\[\\]<>{}]", "");

        if (str.length() == 0) {
            return true;
        }
        if (str.contains("()")) {
            return isIsBalanced(str.replaceFirst("\\(\\)", ""));
        }

        if (str.contains("[]")) {
            return isIsBalanced(str.replaceFirst("\\[]", ""));
        }

        if (str.contains("<>")) {
            return isIsBalanced(str.replaceFirst("<>", ""));
        }

        return str.contains("{}") && isIsBalanced(str.replaceFirst("\\{}", ""));
    }
}
