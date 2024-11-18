package israel.squadra.bootcamp.service;

import israel.squadra.bootcamp.dto.request.PersonRequestDTO;
import israel.squadra.bootcamp.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person create(Person person);
    List<Person> getAll();
    Person update(Person person);
    Optional<Person> getById(Integer id);
    Object getAllParamsPerson(Integer id, String name, String lastName, Integer age, String login, String password, Integer status);
    List<PersonRequestDTO> createPerson(PersonRequestDTO personRequestDTO);
    List<PersonRequestDTO> updatePerson(PersonRequestDTO personRequestDTO);

}
