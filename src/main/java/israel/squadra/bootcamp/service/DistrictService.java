package israel.squadra.bootcamp.service;

import israel.squadra.bootcamp.dto.request.CountyRequestDTO;
import israel.squadra.bootcamp.dto.request.DistrictRequestDTO;
import israel.squadra.bootcamp.model.District;
import israel.squadra.bootcamp.model.Uf;

import java.util.List;
import java.util.Optional;

public interface DistrictService {

    void create(District district);
    List<District> getAll();
    void update(District district);
    Optional<District> getById(Integer id);
    List<DistrictRequestDTO> getAllParamsDistrict(String nome, Integer status, Integer countyId, Integer id);
    List<DistrictRequestDTO> createDistrict(DistrictRequestDTO districtRequestDTO);
    List<DistrictRequestDTO> updateDistrict(DistrictRequestDTO districtRequestDTO);

}


