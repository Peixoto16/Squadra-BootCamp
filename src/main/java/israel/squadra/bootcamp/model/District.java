package israel.squadra.bootcamp.model;

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
@Table(name = "TB_BAIRRO")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_district")
    @SequenceGenerator(name = "generator_district", allocationSize = 1, sequenceName = "BAIRRO_SEQUENCE")
    @Column(name = "CODIGO_BAIRRO")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private County county;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

}
