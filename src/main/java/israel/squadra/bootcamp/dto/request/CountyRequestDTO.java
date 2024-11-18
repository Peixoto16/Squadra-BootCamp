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
public class CountyRequestDTO {


    @JsonProperty("codigoMunicipio")
    private Integer id;

    @NotNull
    @JsonProperty("codigoUF")
    private Integer ufId;

    @NotBlank
    @JsonProperty ("nome")
    private String nome;

    @NotNull
    @JsonProperty ("status")
    private Integer status;

}
