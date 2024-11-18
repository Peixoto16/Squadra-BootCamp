package israel.squadra.bootcamp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import israel.squadra.bootcamp.dto.request.UfRequestDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountyResponseDTO {

    @JsonProperty("codigoMunicipio")
    private Integer id;

    @JsonProperty("codigoUF")
    private Integer ufId;

    @JsonProperty ("nome")
    private String nome;

    @JsonProperty ("status")
    private int status;

    @JsonProperty("uf")
    private UfRequestDTO ufRequestDTO;

}
