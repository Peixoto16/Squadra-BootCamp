package israel.squadra.bootcamp.model;

import israel.squadra.bootcamp.dto.request.UfRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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

    @Column(name = "SIGLA")
    private String sigla;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;


    public static UfRequestDTO toGetDTO(Uf uf) {
        return UfRequestDTO.builder()
                .id(uf.getId())
                .sigla(uf.getSigla())
                .nome(uf.getNome())
                .status(uf.getStatus())
                .build();
    }
}
