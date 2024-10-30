package israel.squadra_bootcamp.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class DomainException extends ResponseStatusException {

    public DomainException(String message, HttpStatus status) {
        super(status, message);
    }

    public DomainException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
