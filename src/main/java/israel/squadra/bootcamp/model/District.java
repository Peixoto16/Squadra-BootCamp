package israel.squadra.bootcamp.model;

import israel.squadra.bootcamp.dto.response.DistrictResponseDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_BAIRRO")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_district")
    @SequenceGenerator(name = "generator_district", allocationSize = 1, sequenceName = "BAIRRO_SEQUENCE")
    @Column(name = "CODIGO_BAIRRO")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private County county;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    public static DistrictResponseDTO toGetDTO(District district) {
        return DistrictResponseDTO.builder()
                .id(district.getId())
                .countyId(district.getCounty().getId())
                .nome(district.getNome())
                .status(district.getStatus())
                .countyResponseDTO(County.toGetDTO(district.getCounty()))
                .build();
    }
}
