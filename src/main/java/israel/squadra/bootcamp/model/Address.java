package israel.squadra.bootcamp.model;

import israel.squadra.bootcamp.dto.response.AddressResponseDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CODIGO_BAIRRO")
    private District district;

    @Column(name = "NUMERO")
    private String number;

    @Column(name = "NOME_RUA")
    private String street;

    @Column(name = "COMPLEMENTO")
    private String complement;

    @Column(name = "CEP")
    private String cep;


    public static AddressResponseDTO toGetDTO(Address address) {
        return AddressResponseDTO.builder()
                .id(address.getId())
                .districtId(address.getDistrict().getId())
                .personId(address.getPerson().getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .cep(address.getCep())
                .districtResponseDTO(District.toGetDTO(address.getDistrict()))
                .build();
    }

}
