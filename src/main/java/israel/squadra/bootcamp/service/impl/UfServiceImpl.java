package israel.squadra.bootcamp.service.impl;

import israel.squadra.bootcamp.controller.exception.DomainException;
import israel.squadra.bootcamp.repository.UfRepository;
import israel.squadra.bootcamp.service.excepValidate.CheckValidate;
import israel.squadra.bootcamp.service.UfService;
import israel.squadra.bootcamp.dto.request.UfRequestDTO;
import israel.squadra.bootcamp.model.Uf;
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
        validateCreateUf(uf);
        repository.save(uf);
    }

    @Override
    public List<Uf> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Uf> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void update(Uf uf) {
        validateUpdateUf(uf);
        repository.save(uf);
    }

    @Override
    public List<UfRequestDTO> getAllParamsUf(String nome, Integer status, String sigla, Integer id) {
        Uf filter = Uf.builder()
                .nome(nome)
                .status(status)
                .sigla(sigla)
                .id(id)
                .build();
        List<Uf> filteredUfs = repository.findAll(Example.of(filter));

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
        create(entity);

        return getAll().stream()
                .map(uf -> modelMapper.map(uf, UfRequestDTO.class))
                .collect(Collectors.toList());
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

        // Retorna a lista atualizada de UFs
        return getAll().stream()
                .map(uf -> modelMapper.map(uf, UfRequestDTO.class))
                .collect(Collectors.toList());
    }

    private void validateCreateUf(Uf uf){

        if (repository.existsBySigla(uf.getSigla())){
            throw new DomainException("Já existe um estado com a sigla " + uf.getSigla() + ".");
        }
        if (repository.existsByNome(uf.getNome())){
            throw new DomainException("Já existe um estado com o nome " + uf.getNome() + ".");
        }

        validateUf(uf);
    }

    private void validateUpdateUf(Uf uf){

        if (repository.existsBySiglaAndIdNot(uf.getSigla(), uf.getId())){
            throw new DomainException("Já existe um outro estado com a sigla " + uf.getSigla() + ".");
        }
        if (repository.existsByNomeAndIdNot(uf.getNome(), uf.getId())){
            throw new DomainException("Já existe um outro estado com o nome " + uf.getNome() + ".");
        }

        validateUf(uf);
    }

    private void validateUf(Uf uf) {

        CheckValidate.checkRequiredInitials(uf.getSigla());

        CheckValidate.checkInitialsLength(uf.getSigla());

        CheckValidate.checkRequiredName(uf.getNome());

        CheckValidate.checkNameLength(uf.getNome(), 60);

        CheckValidate.checkRequiredStatus(uf.getStatus());
    }

}

