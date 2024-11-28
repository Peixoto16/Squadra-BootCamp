package israel.squadra.bootcamp.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdviceApp {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ReturnError> handlerResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(new ReturnError(ex), ex.getStatusCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ReturnError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String message = "Erro de formatação no JSON, verifique e tente novamente!";
        ReturnError returnErro = new ReturnError(new ResponseStatusException(HttpStatus.BAD_REQUEST, message));
        return new ResponseEntity<>(returnErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ReturnError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> validationMessages = extractValidationMessages(ex);
        // Metodo criado especialmente para o Wandin aula pratica 26/11
        String errorMessage = validationMessages.isEmpty()
                ? "Erro de validação desconhecido"
                : String.join(", ", validationMessages);

        ReturnError returnError = new ReturnError(new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage));
        return new ResponseEntity<>(returnError, HttpStatus.BAD_REQUEST);
    }
    private List<String> extractValidationMessages(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> "Campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ReturnError> handleNullPointerException(NullPointerException ex) {
        String message = "O campo '" + ex.getMessage() + "' está ausente. Verifique os dados enviados.";
        ReturnError returnError = new ReturnError(new ResponseStatusException(HttpStatus.BAD_REQUEST, message));
        return new ResponseEntity<>(returnError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ReturnError> handleIllegalArgumentException(IllegalArgumentException ex){
        String message = "O campo '" + ex.getMessage() + "' está ausente. Verifique os dados enviados.";
        ReturnError returnError = new ReturnError(new ResponseStatusException(HttpStatus.BAD_REQUEST, message));
        return new ResponseEntity<>(returnError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ReturnError> handleGeneralException(Exception ex) {
        String message = "Erro interno no servidor. Verifique o codigo e tente novamente !";
        ReturnError returnError = new ReturnError(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message));
        return new ResponseEntity<>(returnError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
