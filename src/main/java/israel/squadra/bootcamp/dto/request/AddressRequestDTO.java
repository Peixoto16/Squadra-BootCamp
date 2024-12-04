package israel.squadra.bootcamp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequestDTO {


    @JsonProperty("codigoEndereco")
    private Integer id;


    @JsonProperty("codigoPessoa")
    private Integer personId;

    @NotNull
    @JsonProperty("codigoBairro")
    private Integer districtId;

    @NotBlank
    @JsonProperty("nomeRua")
    private String street;

    @NotBlank
    @JsonProperty("numero")
    private String number;

    @NotBlank
    @JsonProperty("complemento")
    private String complement;

    @NotBlank
    @JsonProperty("cep")
    private String cep;


}
