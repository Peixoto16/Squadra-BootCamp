package israel.squadra.bootcamp.repository;

import israel.squadra.bootcamp.model.County;
import israel.squadra.bootcamp.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyRepository extends JpaRepository<County, Integer> {

    boolean existsByNomeAndUf(String nome, Uf uf);

}
