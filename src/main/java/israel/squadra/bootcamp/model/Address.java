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
@Table(name = "TB_ENDERECO")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_address")
    @SequenceGenerator(name = "generator_address", allocationSize = 1, sequenceName = "ADDRESS_SEQUENCE")
    @Column(name = "CODIGO_ENDERECO")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CODIGO_PESSOA")
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_BAIRRO")
    private District district;

    @Column(name = "NUMERO")
    private String number;

    @Column(name = "NOME_RUA")
    private String street;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "COMPLEMENTO")
    private String complement;



}
