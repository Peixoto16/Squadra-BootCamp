package israel.squadra.bootcamp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UfRequestDTO {


    @JsonProperty("codigoUF")
    private Integer id;

    @JsonProperty ("nome")
    private String nome;

    @JsonProperty ("sigla")
    private String sigla;

    private Integer status;

}
