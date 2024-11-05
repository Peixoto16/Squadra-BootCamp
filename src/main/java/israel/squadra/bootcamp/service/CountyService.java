package israel.squadra.bootcamp.service;

import israel.squadra.bootcamp.dto.request.CountyRequestDTO;
import israel.squadra.bootcamp.dto.request.UfRequestDTO;
import israel.squadra.bootcamp.model.County;

import java.util.List;
import java.util.Optional;

public interface CountyService {

    void create(County county);
    List<County> getAll();
    void update(County county);
    Optional<County> getById(Integer id);
    List<CountyRequestDTO> getAllParamsCounty(String nome, Integer status, Integer ufId, Integer id);
    List<CountyRequestDTO> createCountys(CountyRequestDTO countyRequestDTO);
    List<CountyRequestDTO> updateCountys(CountyRequestDTO countyRequestDTO);
}
