package israel.squadra.bootcamp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictResponseDTO {

    @JsonProperty("codigoBairro")
    private Integer id;

    @JsonProperty("codigoMunicipio")
    private Integer countyId;

    @JsonProperty ("nome")
    private String nome;

    @JsonProperty ("status")
    private int status;

    @JsonProperty("municipio")
    private CountyResponseDTO countyResponseDTO;
}
