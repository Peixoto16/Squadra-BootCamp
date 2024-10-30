package israel.squadra_bootcamp.controller;

import israel.squadra_bootcamp.dto.request.UfRequestDTO;
import israel.squadra_bootcamp.service.UfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uf")
@RequiredArgsConstructor
public class UfController {

    private final UfService service;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(value = "nome", required = false) String name,
                                         @RequestParam(value = "status", required = false) Integer status,
                                         @RequestParam(value = "sigla", required = false) String sigla,
                                         @RequestParam(value = "codigoUF", required = false) Integer id) {
        List<UfRequestDTO> ufDtos = service.getAllUfs(name, status, sigla, id);
        return ResponseEntity.ok(ufDtos);
    }

    @PostMapping
    public ResponseEntity<List<UfRequestDTO>> create(@RequestBody UfRequestDTO ufRequestDTO) {
        List<UfRequestDTO> ufs = service.createUf(ufRequestDTO);
        return ResponseEntity.ok(ufs);
    }

    @PutMapping
    public ResponseEntity<List<UfRequestDTO>> update(@RequestBody UfRequestDTO ufRequestDTO) {
        List<UfRequestDTO> ufs = service.updateUf(ufRequestDTO);
        return ResponseEntity.ok(ufs); // Retorna 200 OK com a lista atualizada
    }


}
