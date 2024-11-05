package israel.squadra.bootcamp.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ControllerAdviceApp {

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus
    public ResponseEntity handlerResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity(new ReturnError(ex), ex.getStatusCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ReturnError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String message = "Erro de formatação no JSON, verifique e tente novamente!";
        ReturnError returnErros = new ReturnError(new ResponseStatusException(HttpStatus.BAD_REQUEST, message));
        return new ResponseEntity<>(returnErros, HttpStatus.BAD_REQUEST);
    }

}
