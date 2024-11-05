package israel.squadra.bootcamp.service.impl;

import israel.squadra.bootcamp.controller.exception.DomainException;
import israel.squadra.bootcamp.dto.request.CountyRequestDTO;
import israel.squadra.bootcamp.model.County;
import israel.squadra.bootcamp.model.Uf;
import israel.squadra.bootcamp.repository.CountyRepository;
import israel.squadra.bootcamp.service.CheckValidate;
import israel.squadra.bootcamp.service.CountyService;
import israel.squadra.bootcamp.service.UfService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountyServiceImpl implements CountyService {

    private final CountyRepository repository;
    private final UfService ufService;
    private final ModelMapper modelMapper;

    @Override
    public void create(County county) {
        validateCounty(county);
        repository.save(county);
    }

    @Override
    public List<County> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(County county) {
        validateCounty(county);
        repository.save(county);
    }

    @Override
    public Optional<County> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<CountyRequestDTO> getAllParamsCounty(String nome, Integer status, Integer id, Integer ufId) {

        Uf ufEntity = null;
        if(ufId != null) {
            ufEntity = ufService.getById(ufId).orElse(Uf.builder().id(ufId).build());
        }
        County filter = County.builder()
                .nome(nome)
                .status(status)
                .uf(ufEntity)
                .id(id)
                .build();
        List<County> filteredCounty = repository.findAll(Example.of(filter));

        return filteredCounty.stream()
                .map(county -> modelMapper.map(county, CountyRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CountyRequestDTO> createCountys(CountyRequestDTO countyRequestDTO) {

        System.out.println(countyRequestDTO.getUfId());
        Uf ufEntity = ufService.getById(countyRequestDTO.getUfId())
                .orElseThrow(() -> new DomainException("Não existe registro com o código UF "
                        + countyRequestDTO.getUfId(), HttpStatus.NOT_FOUND));

        County entity = County.builder()
                .nome(countyRequestDTO.getNome().trim())
                .uf(ufEntity)
                .status(countyRequestDTO.getStatus())
                .build();
        create(entity);

        return getAll().stream()
                .map(county -> modelMapper.map(county, CountyRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CountyRequestDTO> updateCountys(CountyRequestDTO countyRequestDTO) {

        County entity = getById(countyRequestDTO.getId())
                .orElseThrow( () -> new DomainException("Não existe registro com o código Municipio "
                        + countyRequestDTO.getId(), HttpStatus.NOT_FOUND));

        Uf ufEntity = ufService.getById(countyRequestDTO.getUfId())
                .orElseThrow(() -> new DomainException("Não existe registro com o código UF " + countyRequestDTO.getUfId(),
                        HttpStatus.NOT_FOUND));

        entity.setUf(ufEntity);
        entity.setNome(countyRequestDTO.getNome().trim());
        entity.setStatus(countyRequestDTO.getStatus());
        update(entity);

        return getAll().stream()
                .map(county -> modelMapper.map(county, CountyRequestDTO.class))
                .collect(Collectors.toList());
    }


    private void validateCounty(County county) {

        if (repository.existsByNomeAndUf(county.getNome(), county.getUf())) {
            throw new DomainException("Uma cidade com o nome " + county.getNome()
                    + " já está cadastrada no estado " + county.getUf().getNome()
                    + ". Não é permitido cadastrar duas cidades com o mesmo nome no mesmo estado.");
        }

        CheckValidate.checkRequiredName(county.getNome());

        CheckValidate.checkNameLength(county.getNome(), 256);

        CheckValidate.checkRequiredStatus(county.getStatus());
    }



}

