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
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        validatePerson(person);
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
    public Object getAllParamsPerson(Integer id, String nome, String lastName, Integer age, String login, String password, Integer status) {

        Person filter = Person.builder()
                .id(id)
                .nome(nome)
                .lastName(lastName)
                .age(age)
                .login(login)
                .password(password)
                .status(status)
                .build();
        List<Person> personFilter = repository.findAll(Example.of(filter));


        if (!personFilter.isEmpty() && isPersonFilterByOnlyId(filter)) {
            return personFilter.stream().findFirst().map(Person::toDTOWithAddress);
        }

        return personFilter.stream()
                .map(Person::toDTO);
    }

    private boolean isPersonFilterByOnlyId(Person filter) {
        return filter.getId() != null && (Objects.isNull(filter.getNome())
                && Objects.isNull(filter.getLastName())
                && Objects.isNull(filter.getAge())
                && Objects.isNull(filter.getLogin())
                && Objects.isNull(filter.getPassword())
                && Objects.isNull(filter.getStatus()));
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

        List<Address> addresses = personRequestDTO.getAddressRequestDTO().stream()
                .map(ad -> createAddress(ad, person))
                .peek(CheckValidate::validateAddress)
                .collect(Collectors.toList());

        person.setAddress(addresses);
        update(person);

        return getAll().stream()
                .map(Person::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonRequestDTO> updatePerson(PersonRequestDTO personRequestDTO) {

        return null;
    }




    private void validatePerson(Person person){

        if ((person.getId() == null && repository.existsByLogin(person.getLogin())) ||
                (repository.existsByLoginAndId(person.getLogin(), person.getId()))) {

            throw new DomainException("Login " + person.getLogin()
                    + " já existe, impossível cadastrar.");
        }

        CheckValidate.checkRequiredName(person.getNome());

        CheckValidate.checkRequiredLastName(person.getLastName());

        CheckValidate.checkRequiredAge(person.getAge());

        CheckValidate.checkRequiredLogin(person.getLogin());

        CheckValidate.checkRequiredPassword(person.getPassword());

        CheckValidate.checkLastNameLength(person.getNome(), 256);

        CheckValidate.checkLastNameLength(person.getLastName(), 256);

        CheckValidate.checkAgeLimit(person.getAge());

        CheckValidate.checkLoginLength(person.getLogin(), 50);

        CheckValidate.checkPasswordLength(person.getPassword(), 50);

        CheckValidate.checkRequiredStatus(person.getStatus());
    }


    private Address createAddress(AddressRequestDTO addressRequestDTO, Person person) {
        District district = districtService.getById(addressRequestDTO.getDistrictId())
                .orElseThrow(() -> new DomainException("Registro com o código Bairro "
                        + addressRequestDTO.getDistrictId() + " não existe"));

        return Address.builder()
                .person(person)
                .district(district)
                .street(addressRequestDTO.getStreet())
                .number(addressRequestDTO.getNumber())
                .complement(addressRequestDTO.getComplement())
                .cep(addressRequestDTO.getCep())
                .build();
    }




}


