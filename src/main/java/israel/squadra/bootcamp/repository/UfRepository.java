package israel.squadra.bootcamp.repository;

import israel.squadra.bootcamp.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<Uf, Integer> {

    boolean existsByNome(String nome);
    boolean existsBySigla(String sigla);
    boolean existsByNomeAndIdNot(String nome, Integer id);
    boolean existsBySiglaAndIdNot(String sigla, Integer id);

}
