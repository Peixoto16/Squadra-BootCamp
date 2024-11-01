package israel.squadra.bootcamp.controller.exception;

import israel.squadra.bootcamp.controller.exception.ReturnErros;
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
        return new ResponseEntity(new ReturnErros(ex), ex.getStatusCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ReturnErros> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String message = "Erro de formatação no JSON, verifique e tente novamente!";
        ReturnErros returnErros = new ReturnErros(new ResponseStatusException(HttpStatus.BAD_REQUEST, message));
        return new ResponseEntity<>(returnErros, HttpStatus.BAD_REQUEST);
    }

}
