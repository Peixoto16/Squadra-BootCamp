package israel.squadra.bootcamp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonResponseDTO {

    @JsonProperty("codigoPessoa")
    private Integer id;

    @JsonProperty ("nome")
    private String nome;

    @JsonProperty("sobrenome")
    private String lastName;

    @JsonProperty("idade")
    private Integer age;

    @JsonProperty("login")
    private String login;

    @JsonProperty("senha")
    private String password;

    @JsonProperty("status")
    private int status;

    @JsonProperty("enderecos")
    private List<AddressResponseDTO> addressResponseDTOs;
}
