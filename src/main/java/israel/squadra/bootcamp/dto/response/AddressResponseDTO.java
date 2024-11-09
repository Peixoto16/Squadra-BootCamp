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
public class AddressResponseDTO {

    @JsonProperty("codigoEndereco")
    private Integer id;

    @JsonProperty("codigoBairro")
    private Integer districtId;

    @JsonProperty("codigoPessoa")
    private Integer personId;

    @JsonProperty("nomeRua")
    private String street;

    @JsonProperty("numero")
    private String number;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("bairro")
    private DistrictResponseDTO districtResponseDTO;
}
