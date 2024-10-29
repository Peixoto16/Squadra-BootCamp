package israel.squadra_bootcamp.repository;

import israel.squadra_bootcamp.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<Uf, Integer> {


}
