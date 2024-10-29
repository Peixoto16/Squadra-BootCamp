package israel.squadra_bootcamp.model;

import israel.squadra_bootcamp.dto.request.UfRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_UF")
public class Uf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerador_uf")
    @SequenceGenerator(name = "gerador_uf", allocationSize = 1, sequenceName = "UF_SEQUENCIA")
    @Column(name = "CODIGO_UF")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SIGLA")
    private String sigla;

    @Column(name = "STATUS")
    private Integer status;

    public static UfRequestDTO retornoDTO(Uf uf) {
        return UfRequestDTO.builder()
                .id(uf.getId())
                .sigla(uf.getSigla())
                .nome(uf.getNome())
                .status(uf.getStatus())
                .build();
    }

}
