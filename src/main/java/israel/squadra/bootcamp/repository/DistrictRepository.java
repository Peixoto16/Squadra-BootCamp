package israel.squadra.bootcamp.repository;

import israel.squadra.bootcamp.model.County;
import israel.squadra.bootcamp.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    boolean existsByNomeAndCounty(String nome, County county);

}
