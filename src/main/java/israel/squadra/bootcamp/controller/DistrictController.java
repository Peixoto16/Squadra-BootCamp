package israel.squadra.bootcamp.controller;

import israel.squadra.bootcamp.dto.request.CountyRequestDTO;
import israel.squadra.bootcamp.dto.request.DistrictRequestDTO;
import israel.squadra.bootcamp.dto.request.UfRequestDTO;
import israel.squadra.bootcamp.model.District;
import israel.squadra.bootcamp.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bairro")
public class DistrictController {

    private final DistrictService service;

    @PostMapping
    public ResponseEntity<List<DistrictRequestDTO>> create(@RequestBody DistrictRequestDTO districtRequestDTO) {
        List<DistrictRequestDTO> districts = service.createDistrict(districtRequestDTO);
        return ResponseEntity.ok(districts);
    }

    @GetMapping
    public ResponseEntity <Object> getAll(@RequestParam(value = "nome", required = false) String nome,
                                          @RequestParam(value ="status", required = false) Integer status,
                                          @RequestParam(value ="codigoMunicipio", required = false) Integer countyId,
                                          @RequestParam(value = "codigoBairro", required = false) Integer id) {
        List<DistrictRequestDTO> districts = service.getAllParamsDistrict(nome, status, countyId, id);
        return ResponseEntity.ok(districts);
    }

    @PutMapping
    public ResponseEntity <List<DistrictRequestDTO>> update(@RequestBody DistrictRequestDTO districtRequestDTO) {
        List<DistrictRequestDTO> districts = service.updateDistrict(districtRequestDTO);
        return ResponseEntity.ok(districts);
    }


}
