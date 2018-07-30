package paul.leavitt.todo.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class TodoValidationExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request
    ) {
        BindingResult bindingResult = ex
                .getBindingResult();

        List<TodoFieldError> todoFieldErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> new TodoFieldError(
                        fieldError.getCode(),
                        fieldError.getDefaultMessage(),
                        fieldError.getRejectedValue())
                )
                .collect(toList());

        TodoErrorView todoErrorView = new TodoErrorView(todoFieldErrors, "ValidationError");

        return new ResponseEntity<>(todoErrorView, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TodoNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(final TodoNotFoundException ex) {

        List<TodoFieldError> todoFieldErrors = Collections.singletonList(new TodoFieldError(ex.getLocalizedMessage()));

        TodoErrorView todoErrorView = new TodoErrorView(todoFieldErrors, "NotFoundError");

        return new ResponseEntity<>(todoErrorView, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

