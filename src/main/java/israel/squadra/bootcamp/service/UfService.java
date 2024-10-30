package israel.squadra_bootcamp.service;

import israel.squadra_bootcamp.dto.request.UfRequestDTO;
import israel.squadra_bootcamp.model.Uf;

import java.util.List;
import java.util.Optional;

public interface UfService {
    void create(Uf uf);
    List<Uf> getUfs();
    List<Uf> getAll(Uf filter);
    void update(Uf uf);
    Optional<Uf> getById(Integer id);
    List<UfRequestDTO> getAllUfs(String name, Integer status, String sigla, Integer id);
    List<UfRequestDTO> createUf(UfRequestDTO ufRequestDTO);
    List<UfRequestDTO> updateUf(UfRequestDTO ufRequestDTO);

}
