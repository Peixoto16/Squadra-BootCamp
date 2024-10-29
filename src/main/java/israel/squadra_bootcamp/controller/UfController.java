package israel.squadra_bootcamp.controller;

import israel.squadra_bootcamp.dto.request.UfRequestDTO;
import israel.squadra_bootcamp.model.Uf;
import israel.squadra_bootcamp.service.UfService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/uf")
@RequiredArgsConstructor
public class UfController {


    private final UfService service;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<List<UfRequestDTO>> create(@RequestBody UfRequestDTO ufRequestDTO) {

        Uf entity = Uf.builder()
                .id(ufRequestDTO.getId())
                .sigla(ufRequestDTO.getSigla().trim())
                .nome(ufRequestDTO.getNome().trim())
                .status(ufRequestDTO.getStatus())
                .build();
        service.create(entity);
        // Obtém a lista atualizada de Ufs e a converte para UfDTO
        List<UfRequestDTO> ufs = service.getUfs().stream()
                .map(uf -> modelMapper.map(uf, UfRequestDTO.class))
                .collect(Collectors.toList());
        // 200 ok
        return ResponseEntity.ok(ufs);
    }



}
