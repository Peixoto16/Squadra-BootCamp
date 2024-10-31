package israel.squadra.bootcamp.controller.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.server.ResponseStatusException;

@Getter
@Setter
public class ReturnErros {

    @JsonProperty("mensagem")
    private String msg;
    private int status;

    public ReturnErros(ResponseStatusException ex) {
        this.msg = ex.getReason();
        this.status = ex.getStatusCode().value();
    }
}
