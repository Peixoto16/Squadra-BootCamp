package israel.squadra.bootcamp.controller;

import israel.squadra.bootcamp.dto.request.CountyRequestDTO;
import israel.squadra.bootcamp.dto.request.UfRequestDTO;
import israel.squadra.bootcamp.model.County;
import israel.squadra.bootcamp.service.CountyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/municipio")
public class CountyController {

    private final CountyService service;

    @PostMapping
    public ResponseEntity<List<CountyRequestDTO>> create(@RequestBody CountyRequestDTO countyRequestDTO) {
        List<CountyRequestDTO> countys = service.createCountys(countyRequestDTO);
        return ResponseEntity.ok(countys);
    }

    @PutMapping
    public ResponseEntity<List<CountyRequestDTO>> update(@RequestBody CountyRequestDTO countyRequestDTO) {
        List<CountyRequestDTO> countys = service.updateCountys(countyRequestDTO);
        return ResponseEntity.ok(countys);
    }
   @GetMapping
   public ResponseEntity <Object> getAll(@RequestParam(value = "nome", required = false) String nome,
                                         @RequestParam(value ="status", required = false) Integer status,
                                         @RequestParam(value ="codigoMunicipio", required = false) Integer id,
                                         @RequestParam(value = "codigoUF", required = false) Integer ufId) {
       Object countys = service.getAllParamsCounty(nome, status, id, ufId);
       return ResponseEntity.ok(countys);
   }

}
