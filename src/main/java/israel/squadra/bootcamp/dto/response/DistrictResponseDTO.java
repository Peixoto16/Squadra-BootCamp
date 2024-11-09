package israel.squadra.bootcamp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
