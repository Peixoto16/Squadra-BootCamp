package israel.squadra.bootcamp.controller;

import israel.squadra.bootcamp.dto.request.UfRequestDTO;
import israel.squadra.bootcamp.service.UfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/uf")
public class UfController {

    private final UfService service;

    @GetMapping
    public ResponseEntity<Object> getAllParams(@RequestParam(value = "nome", required = false) String name,
                                         @RequestParam(value = "status", required = false) Integer status,
                                         @RequestParam(value = "sigla", required = false) String sigla,
                                         @RequestParam(value = "codigoUF", required = false) Integer id) {
        Object ufs = service.getAllParamsUf(name, status, sigla, id);
        return ResponseEntity.ok(ufs);
    }

    @PostMapping
    public ResponseEntity<List<UfRequestDTO>> create(@RequestBody UfRequestDTO ufRequestDTO) {
        List<UfRequestDTO> ufs = service.createUf(ufRequestDTO);
        return ResponseEntity.ok(ufs);
    }

    @PutMapping
    public ResponseEntity<List<UfRequestDTO>> update(@RequestBody UfRequestDTO ufRequestDTO) {
        List<UfRequestDTO> ufs = service.updateUf(ufRequestDTO);
        return ResponseEntity.ok(ufs);
    }

    @DeleteMapping
    public ResponseEntity<List<UfRequestDTO>> delete(@RequestParam(value = "codigoUF", required = false) Integer id){
        List<UfRequestDTO> ufs = service.deleteUf(id);
        return ResponseEntity.ok(ufs);
    }

}
