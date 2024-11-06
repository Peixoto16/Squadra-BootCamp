package israel.squadra.bootcamp.repository;

import israel.squadra.bootcamp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    boolean existsByLoginAndId(String login, Integer id);
    boolean existsByLogin(String login);

}
