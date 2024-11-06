package israel.squadra.bootcamp.service;

import israel.squadra.bootcamp.dto.request.PersonRequestDTO;
import israel.squadra.bootcamp.dto.request.UfRequestDTO;
import israel.squadra.bootcamp.model.Person;
import israel.squadra.bootcamp.model.Uf;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    void create(Person person);
    List<Person> getAll();
    void update(Person person);
    Optional<Person> getById(Integer id);
    List<PersonRequestDTO> getAllParamsPerson(Integer id, String name, String lastName, Integer age, String login,String password,Integer status);
    List<PersonRequestDTO> createPerson(PersonRequestDTO personRequestDTO);
    List<PersonRequestDTO> updatePerson(PersonRequestDTO personRequestDTO);

}
