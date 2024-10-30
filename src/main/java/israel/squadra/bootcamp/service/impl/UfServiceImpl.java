package israel.squadra_bootcamp.service.impl;

import israel.squadra_bootcamp.controller.exception.DomainException;
import israel.squadra_bootcamp.repository.UfRepository;
import israel.squadra_bootcamp.service.UfService;
import israel.squadra_bootcamp.dto.request.UfRequestDTO;
import israel.squadra_bootcamp.model.Uf;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UfServiceImpl implements UfService {

    private final UfRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public void create(Uf uf) {
        repository.save(uf);
    }

    @Override
    public List<Uf> getUfs() {
        return repository.findAll();
    }

    @Override
    public List<Uf> getAll(Uf filter) {
        return repository.findAll(Example.of(filter));
    }

    @Override
    public Optional<Uf> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void update(Uf uf) {
        repository.save(uf);
    }

    @Override
    public List<UfRequestDTO> getAllUfs(String name, Integer status, String sigla, Integer id) {
        Uf filter = Uf.builder()
                .nome(name)
                .status(status)
                .sigla(sigla)
                .id(id)
                .build();

        List<Uf> filteredUfs = getAll(filter);
        return filteredUfs.stream()
                .map(uf -> modelMapper.map(uf, UfRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UfRequestDTO> createUf(UfRequestDTO ufRequestDTO) {
        Uf entity = Uf.builder()
                .id(ufRequestDTO.getId())
                .sigla(ufRequestDTO.getSigla().trim())
                .nome(ufRequestDTO.getNome().trim())
                .status(ufRequestDTO.getStatus())
                .build();
        create(entity); // Salva a entidade no repositório
        return getAllUfs(null, null, null, null); // Retorna a lista atualizada de UFs
    }

    @Override
    public List<UfRequestDTO> updateUf(UfRequestDTO ufRequestDTO) {
        Uf entity = getById(ufRequestDTO.getId())
                .orElseThrow(() -> new DomainException("Não existe registro com o código UF " + ufRequestDTO.getId(),
                        HttpStatus.NOT_FOUND));

        entity.setNome(ufRequestDTO.getNome());
        entity.setSigla(ufRequestDTO.getSigla());
        entity.setStatus(ufRequestDTO.getStatus());

        update(entity);

        return getUfs().stream()
                .map(uf -> modelMapper.map(uf, UfRequestDTO.class))
                .collect(Collectors.toList());
    }


}

