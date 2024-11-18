package israel.squadra.bootcamp.service.impl;

import israel.squadra.bootcamp.controller.exception.DomainException;
import israel.squadra.bootcamp.dto.request.AddressRequestDTO;
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

    @Override
    public Person create(Person person) {
        validatePerson(person);
        return save(person);
    }

    @Override
    public Person update(Person person) {
        validatePerson(person);
        return save(person);
    }
    private Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public List<Person> getAll() {
        return repository.findAll();
    }
    @Override
    public Optional<Person> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Object getAllParamsPerson(Integer id, String nome, String lastName, Integer age, String login, String password, Integer status) {

        Person select = Person.builder()
                .id(id)
                .nome(nome)
                .lastName(lastName)
                .age(age)
                .login(login)
                .password(password)
                .status(status)
                .build();
        List<Person> selectPerson = repository.findAll(Example.of(select));


        if (!selectPerson.isEmpty() && isPersonFilterByOnlyId(select)) {
            return selectPerson.stream().findFirst().map(Person::toDTOWithAddress);
        }

        return selectPerson.stream()
                .map(Person::toDTO);
    }

    private boolean isPersonFilterByOnlyId(Person select) {
        return select.getId() != null && (Objects.isNull(select.getNome())
                && Objects.isNull(select.getLastName())
                && Objects.isNull(select.getAge())
                && Objects.isNull(select.getLogin())
                && Objects.isNull(select.getPassword())
                && Objects.isNull(select.getStatus()));
    }

    @Override
    public List<PersonRequestDTO> createPerson(PersonRequestDTO personRequestDTO) {

        Person model = Person.builder()
                .nome(personRequestDTO.getNome())
                .lastName(personRequestDTO.getLastName())
                .age(personRequestDTO.getAge())
                .login(personRequestDTO.getLogin())
                .password(personRequestDTO.getPassword())
                .status(personRequestDTO.getStatus())
                .build();
        Person person = create(model);

        List<Address> addresses = personRequestDTO.getAddressRequestDTO().stream()
                .map(a -> createAddress(a, person))
                .peek(CheckValidate::validateAddress)
                .collect(Collectors.toList());

        person.setAddress(addresses);
        save(model);

        return getAll().stream()
                .map(Person::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonRequestDTO> updatePerson(PersonRequestDTO personRequestDTO) {

        Person model = getById(personRequestDTO.getId())
                .orElseThrow(() -> new DomainException("Não existe registro com o código Pessoa "
                        + personRequestDTO.getId()));

        model.setNome(personRequestDTO.getNome());
        model.setLastName(personRequestDTO.getLastName());
        model.setAge(personRequestDTO.getAge());
        model.setLogin(personRequestDTO.getLogin());
        model.setPassword(personRequestDTO.getPassword());
        model.setStatus(personRequestDTO.getStatus());

        List<Address> addresses = personRequestDTO.getAddressRequestDTO().stream()
                .map(dto -> createOrUpdate(model, dto))  // Cria ou atualiza o endereço
                .peek(CheckValidate::validateAddress)    // Valida o endereço
                .collect(Collectors.toList());

        List<Address> addressToRemove = model.getAddress().stream()
                .filter(address -> !addresses.contains(address))
                .collect(Collectors.toList());

        model.getAddress().removeAll(addressToRemove);

        addressService.delete(addressToRemove);

        model.getAddress().addAll(addresses);
        update(model);

        return getAll().stream()
                .map(Person::toDTO)
                .collect(Collectors.toList());
    }

    private Address createOrUpdate(Person model, AddressRequestDTO requestDTO) {

        if (requestDTO.getId() == null || requestDTO.getId() == 0) {
            return createAddress(requestDTO, model);
        }

        return updateAddress(requestDTO, model.getAddress());
    }

    private Address updateAddress(AddressRequestDTO addressRequestDTO, List<Address> addresses) {
        District district = districtService.getById(addressRequestDTO.getDistrictId())
                .orElseThrow(() -> new DomainException("Registro com o código Bairro "
                        + addressRequestDTO.getDistrictId() + " não existe"));

        return addresses.stream()
                .filter(address -> address.getId().equals(addressRequestDTO.getId()))
                .peek(a -> {
                    a.setDistrict(district);
                    a.setCep(addressRequestDTO.getCep());
                    a.setComplement(addressRequestDTO.getComplement());
                    a.setNumber(addressRequestDTO.getNumber());
                    a.setStreet(addressRequestDTO.getStreet());
                }).findFirst()
                .orElseThrow(() -> new DomainException("Erro ao tentar atualizar os endereços"));
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

    private void validatePerson(Person person){

        if ((person.getId() == null && repository.existsByLogin(person.getLogin())) ||
                (repository.existsByLoginAndId(person.getLogin(), person.getId()))) {

            throw new DomainException("Login " + person.getLogin()
                    + " já existe, impossível cadastrar.");
        }

        CheckValidate.checkLastNameLength(person.getNome(), 256);

        CheckValidate.checkLastNameLength(person.getLastName(), 256);

        CheckValidate.checkAgeLimit(person.getAge());

        CheckValidate.checkLoginLength(person.getLogin(), 50);

        CheckValidate.checkPasswordLength(person.getPassword(), 50);

        CheckValidate.checkRequiredStatus(person.getStatus());
    }





}


