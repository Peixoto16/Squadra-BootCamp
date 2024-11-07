package israel.squadra.bootcamp.controller;

import israel.squadra.bootcamp.dto.request.PersonRequestDTO;
import israel.squadra.bootcamp.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/pessoa")
public class PersonController {

    private final PersonService service;


    @PostMapping
    public ResponseEntity<List<PersonRequestDTO>> create(@RequestBody PersonRequestDTO personRequestDTO){
        List<PersonRequestDTO> persons = service.createPerson(personRequestDTO);
        return ResponseEntity.ok(persons);
    }
}
