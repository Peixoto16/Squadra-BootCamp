package israel.squadra.bootcamp.service.impl;

import israel.squadra.bootcamp.controller.PersonRepository;
import israel.squadra.bootcamp.dto.request.PersonRequestDTO;
import israel.squadra.bootcamp.model.Person;
import israel.squadra.bootcamp.service.AddressService;
import israel.squadra.bootcamp.service.DistrictService;
import israel.squadra.bootcamp.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {


    private final AddressService addressService;
    private final DistrictService districtService;
    private final PersonRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public void create(Person person) {

    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public Optional<Person> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<PersonRequestDTO> getAllParamsPerson(Integer id, String name, String lastName, Integer age, String login, String password, Integer status) {
        return null;
    }

    @Override
    public List<PersonRequestDTO> createPerson(PersonRequestDTO personRequestDTO) {
        return null;
    }

    @Override
    public List<PersonRequestDTO> updatePerson(PersonRequestDTO personRequestDTO) {
        return null;
    }
}
