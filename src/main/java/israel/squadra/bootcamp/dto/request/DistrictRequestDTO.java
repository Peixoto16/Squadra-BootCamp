package israel.squadra.bootcamp.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictRequestDTO {


    @JsonProperty("codigoBairro")
    private Integer id;

    @NotNull
    @JsonProperty("codigoMunicipio")
    private Integer countyId;

    @NotBlank
    @JsonProperty ("nome")
    private String nome;

    @NotNull
    @JsonProperty ("status")
    private Integer status;

}

