package israel.squadra.bootcamp.controller.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.server.ResponseStatusException;

@Getter
@Setter
@NoArgsConstructor
public class ReturnError {

    @JsonProperty("mensagem")
    private String msg;
    private int status;

    public ReturnError(ResponseStatusException ex) {
        this.msg = ex.getReason(); // retorna a razao do erro
        this.status = ex.getStatusCode().value(); // retorna o obj http e obtem o numero do erro
    }

}
