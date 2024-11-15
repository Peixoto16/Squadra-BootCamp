package israel.squadra.bootcamp.model;

import israel.squadra.bootcamp.dto.response.CountyResponseDTO;
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
@Table(name = "TB_MUNICIPIO")
public class County {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "county_generator")
    @SequenceGenerator(name = "county_generator", allocationSize = 1, sequenceName = "MUNICIPIO_SEQUENCE")
    @Column(name = "CODIGO_MUNICIPIO")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CODIGO_UF")
    private Uf uf;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;


    public static CountyResponseDTO toGetDTO(County county) {
        return CountyResponseDTO.builder()
                .id(county.getId())
                .nome(county.getNome())
                .ufRequestDTO(Uf.toGetDTO(county.getUf()))
                .ufId(county.getUf().getId())
                .status(county.getStatus())
                .build();
    }
}
