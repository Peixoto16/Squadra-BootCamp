package israel.squadra.bootcamp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequestDTO {


    @JsonProperty("codigoEndereco")
    private Integer id;

    @JsonProperty("codigoPessoa")
    private Integer personId;

    @JsonProperty("codigoBairro")
    private Integer districtId;

    @JsonProperty("nomeRua")
    private String street;

    @JsonProperty("numero")
    private String number;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("complemento")
    private String complement;

}
