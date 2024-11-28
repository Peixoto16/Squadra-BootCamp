package israel.squadra.bootcamp.controller;

import israel.squadra.bootcamp.dto.request.PersonRequestDTO;
import israel.squadra.bootcamp.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")
public class PersonController {

    private final PersonService service;


    @PostMapping
    public ResponseEntity <List<PersonRequestDTO>> create(@RequestBody @Valid PersonRequestDTO personRequestDTO){
        List<PersonRequestDTO> persons = service.createPerson(personRequestDTO);
        return ResponseEntity.ok(persons);
    }

    @GetMapping
    public ResponseEntity <Object> getAll(@RequestParam(value = "codigoPessoa", required = false) Integer id,
                                               @RequestParam(value = "nome", required = false) String nome,
                                               @RequestParam(value = "sobrenome", required = false) String lastName,
                                               @RequestParam(value = "idade", required = false) Integer age,
                                               @RequestParam(value = "login", required = false) String login,
                                               @RequestParam(value = "senha", required = false) String password,
                                               @RequestParam(value ="status", required = false) Integer status){
        Object persons = service.getAllParamsPerson(id, nome, lastName, age, login, password, status);
        return ResponseEntity.ok(persons);
    }

    @PutMapping
    public ResponseEntity <List<PersonRequestDTO>> update(@RequestBody @Valid PersonRequestDTO personRequestDTO){
        List<PersonRequestDTO> persons = service.updatePerson(personRequestDTO);
        return ResponseEntity.ok(persons);
    }


}
