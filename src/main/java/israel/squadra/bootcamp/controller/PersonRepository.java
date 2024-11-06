package israel.squadra.bootcamp.controller;

import israel.squadra.bootcamp.service.PersonService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/pessoa")
public class PersonRepository {

    private final PersonService service;



}
