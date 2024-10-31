package israel.squadra.bootcamp.controller;

import israel.squadra.bootcamp.controller.exception.ReturnErros;
import org.springframework.http.ResponseEntity;
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

}
