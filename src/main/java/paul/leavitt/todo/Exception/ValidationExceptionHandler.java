package paul.leavitt.todo.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status,
            WebRequest request
    ) {
        BindingResult bindingResult = exception
                .getBindingResult();

        List<FieldError> fieldErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldError(
                        "params",
                        fieldError.getCode(),
                        fieldError.getDefaultMessage(),
                        fieldError.getRejectedValue())
                )
                .collect(toList());

        ErrorView errorView = new ErrorView(fieldErrors, "ValidationError");

        return new ResponseEntity<>(errorView, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(TodoNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(final TodoNotFoundException ex) {

        List<FieldError> fieldErrors = Collections.singletonList(new FieldError(ex.getLocalizedMessage()));

        ErrorView errorView = new ErrorView(fieldErrors, "NotFoundError");

        return new ResponseEntity<>(errorView, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        List<FieldError> fieldErrors = exception.getConstraintViolations()
                .stream()
                .map(fieldError -> new FieldError(
                        "params",
                        fieldError.getPropertyPath().toString().split("\\.")[1],
                        fieldError.getMessage(),
                        fieldError.getInvalidValue())
                )
                .collect(toList());

        ErrorView errorView = new ErrorView(fieldErrors, "ValidationError");
        return new ResponseEntity<>(errorView, HttpStatus.BAD_REQUEST);
    }

}

