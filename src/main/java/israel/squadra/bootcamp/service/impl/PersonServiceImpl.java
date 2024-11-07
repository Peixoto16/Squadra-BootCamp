package israel.squadra.bootcamp.service.impl;

import israel.squadra.bootcamp.controller.exception.DomainException;
import israel.squadra.bootcamp.dto.request.AddressRequestDTO;
import israel.squadra.bootcamp.dto.request.DistrictRequestDTO;
import israel.squadra.bootcamp.dto.request.PersonRequestDTO;
import israel.squadra.bootcamp.model.Address;
import israel.squadra.bootcamp.model.District;
import israel.squadra.bootcamp.model.Person;
import israel.squadra.bootcamp.repository.PersonRepository;
import israel.squadra.bootcamp.service.AddressService;
import israel.squadra.bootcamp.service.DistrictService;
import israel.squadra.bootcamp.service.PersonService;
import israel.squadra.bootcamp.service.excepValidate.CheckValidate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {


    private final PersonRepository repository;
    private final AddressService addressService;
    private final DistrictService districtService;
    private final ModelMapper modelMapper;

    @Override
    public Person create(Person person) {
      return repository.save(person);
    }

    @Override
    public List<Person> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(Person person) {
        repository.save(person);
    }

    @Override
    public Optional<Person> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<PersonRequestDTO> getAllParamsPerson(Integer id, String name, String lastName, Integer age, String login, String password, Integer status) {
        return null;
    }

    @Override
    public List<PersonRequestDTO> createPerson(PersonRequestDTO personRequestDTO) {

        Person entity = Person.builder()
                .nome(personRequestDTO.getNome())
                .lastName(personRequestDTO.getLastName())
                .age(personRequestDTO.getAge())
                .login(personRequestDTO.getLogin())
                .password(personRequestDTO.getPassword())
                .status(personRequestDTO.getStatus())
                .build();
        Person person = create(entity);

        List<Address> addresses = personRequestDTO.getAddressDTOs().stream()
                .map(address -> createAddress(address, person))
                .peek(CheckValidate::validateAddress)
                .collect(Collectors.toList());

        person.setAddress(addresses);
        update(entity);

        return getAll().stream()
                .map(p -> modelMapper.map(p, PersonRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonRequestDTO> updatePerson(PersonRequestDTO personRequestDTO) {

        return null;
    }


    private Address createAddress(AddressRequestDTO addressRequestDTO, Person person) {
        District district = districtService.getById(addressRequestDTO.getDistrictId())
                .orElseThrow(() -> new DomainException("Não existe registro com o código Bairro "
                        + addressRequestDTO.getDistrictId()));

        return Address.builder()
                .person(person)
                .district(district)
                .number(addressRequestDTO.getNumber())
                .street(addressRequestDTO.getStreet())
                .complement(addressRequestDTO.getComplement())
                .cep(addressRequestDTO.getCep())
                .build();
    }


}


