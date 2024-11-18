package israel.squadra.bootcamp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequestDTO {


    @JsonProperty("codigoPessoa")
    private Integer id;

    @NotBlank
    @JsonProperty ("nome")
    private String nome;

    @NotBlank
    @JsonProperty("sobrenome")
    private String lastName;

    @NotBlank
    @JsonProperty("idade")
    private Integer age;

    @NotBlank
    @JsonProperty("login")
    private String login;

    @NotBlank
    @JsonProperty("senha")
    private String password;

    @NotNull
    @JsonProperty("status")
    private Integer status;

    @NotNull
    @JsonProperty("enderecos")
    private List<AddressRequestDTO> addressRequestDTO;

}
