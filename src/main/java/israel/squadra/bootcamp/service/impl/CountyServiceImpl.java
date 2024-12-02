package israel.squadra.bootcamp.service.impl;

import israel.squadra.bootcamp.controller.exception.DomainException;
import israel.squadra.bootcamp.dto.request.CountyRequestDTO;
import israel.squadra.bootcamp.model.County;
import israel.squadra.bootcamp.model.Uf;
import israel.squadra.bootcamp.repository.CountyRepository;
import israel.squadra.bootcamp.service.excepValidate.CheckValidate;
import israel.squadra.bootcamp.service.CountyService;
import israel.squadra.bootcamp.service.UfService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public Object getAllParamsCounty(String nome, Integer status, Integer id, Integer ufId) {

        Uf ufModel = null;
        if(ufId != null) {
            ufModel = ufService.getById(ufId).orElse(Uf.builder().id(ufId).build());
        }
        County select = County.builder()
                .nome(nome)
                .status(status)
                .uf(ufModel)
                .id(id)
                .build();
        List<County> selectCounty = repository.findAll(Example.of(select));

        if (isCountySelectBy(select) && !selectCounty.isEmpty()) {
            return selectCounty.stream().findFirst().map(city -> modelMapper.map(city, CountyRequestDTO.class));
        }

        return selectCounty.stream()
                .map(county -> modelMapper.map(county, CountyRequestDTO.class))
                .collect(Collectors.toList());
    }

    private boolean isCountySelectBy(County select) {
        return (Objects.nonNull(select.getId())
                || Objects.nonNull(select.getNome()))
                || (Objects.nonNull(select.getStatus())
                && Objects.nonNull(select.getUf()));
    }

    @Override
    public List<CountyRequestDTO> createCountys(CountyRequestDTO countyRequestDTO) {

        Uf ufModel = ufService.getById(countyRequestDTO.getUfId())
                .orElseThrow(() -> new DomainException("Não existe registro com o código UF "
                        + countyRequestDTO.getUfId(), HttpStatus.NOT_FOUND));

        County model = County.builder()
                .nome(countyRequestDTO.getNome().trim())
                .uf(ufModel)
                .status(countyRequestDTO.getStatus())
                .build();
        create(model);

        return getAll().stream()
                .map(county -> modelMapper.map(county, CountyRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CountyRequestDTO> updateCountys(CountyRequestDTO countyRequestDTO) {

        County model = getById(countyRequestDTO.getId())
                .orElseThrow( () -> new DomainException("Não existe registro com o código Municipio "
                        + countyRequestDTO.getId(), HttpStatus.NOT_FOUND));

        Uf ufModel = ufService.getById(countyRequestDTO.getUfId())
                .orElseThrow(() -> new DomainException("Não existe registro com o código UF " + countyRequestDTO.getUfId(),
                        HttpStatus.NOT_FOUND));

        model.setUf(ufModel);
        model.setNome(countyRequestDTO.getNome().trim());
        model.setStatus(countyRequestDTO.getStatus());
        update(model);

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

        CheckValidate.checkNameLength(county.getNome(), 256);

        CheckValidate.checkRequiredStatus(county.getStatus());

    }



}

