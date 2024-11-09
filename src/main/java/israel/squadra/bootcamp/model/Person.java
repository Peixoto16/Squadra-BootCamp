package israel.squadra.bootcamp.model;

import israel.squadra.bootcamp.dto.request.PersonRequestDTO;
import israel.squadra.bootcamp.dto.response.PersonResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_PESSOA")
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_person")
    @SequenceGenerator(name = "generator_person", allocationSize = 1, sequenceName = "PESSOA_SEQUENCE")
    @Column(name = "CODIGO_PESSOA")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String lastName;

    @Column(name = "IDADE")
    private Integer age;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    private String password;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> address;

    public void setAddress(List<Address> address) {
        if(!CollectionUtils.isEmpty(this.address)) {
            this.address.clear();
        }
        this.address = address;
    }

    public static PersonRequestDTO toDTO(Person person){

        return PersonRequestDTO.builder()
                .id(person.getId())
                .nome(person.getNome())
                .lastName(person.getLastName())
                .age(person.getAge())
                .login(person.getLogin())
                .password(person.getPassword())
                .status(person.getStatus())
                .addressRequestDTO(new ArrayList<>())
                .build();
    }

    public static PersonResponseDTO toDTOWithAddress(Person person) {
        return PersonResponseDTO.builder()
                .id(person.getId())
                .nome(person.getNome())
                .lastName(person.getLastName())
                .age(person.getAge())
                .login(person.getLogin())
                .password(person.getPassword())
                .status(person.getStatus())
                .addressResponseDTOs(person.getAddress().stream()
                        .map(Address::toGetDTO).collect(Collectors.toList()))
                .build();
    }


}
