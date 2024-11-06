package israel.squadra.bootcamp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

}
