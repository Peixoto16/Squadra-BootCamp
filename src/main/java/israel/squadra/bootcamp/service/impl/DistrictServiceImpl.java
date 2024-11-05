package israel.squadra.bootcamp.service.impl;

import israel.squadra.bootcamp.controller.exception.DomainException;
import israel.squadra.bootcamp.dto.request.DistrictRequestDTO;
import israel.squadra.bootcamp.model.County;
import israel.squadra.bootcamp.model.District;
import israel.squadra.bootcamp.repository.DistrictRepository;
import israel.squadra.bootcamp.service.CheckValidate;
import israel.squadra.bootcamp.service.CountyService;
import israel.squadra.bootcamp.service.DistrictService;
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
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository repository;
    private final CountyService countyService;
    private final ModelMapper modelMapper;

    @Override
    public void create(District district) {
        validateDistrict(district);
        repository.save(district);
    }

    @Override
    public List<District> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(District district) {
        validateDistrict(district);
        repository.save(district);
    }

    @Override
    public Optional<District> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<DistrictRequestDTO> getAllParamsDistrict(String nome, Integer status, Integer countyId, Integer id) {
        County countyEntity = null;

        if(countyId != null) {
            countyEntity = countyService.getById(countyId).orElse(County.builder().id(countyId).build());
        }

        District filter = District.builder()
                .nome(nome)
                .status(status)
                .county(countyEntity)
                .id(id)
                .build();

        List<District> filteredDistrict = repository.findAll(Example.of(filter));

        return filteredDistrict.stream()
                .map(district -> modelMapper.map(district, DistrictRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DistrictRequestDTO> createDistrict(DistrictRequestDTO districtRequestDTO) {

        County countyEntity = countyService.getById(districtRequestDTO.getCountyId())
                .orElseThrow(() -> new DomainException("Não existe registro com o código Município "
                        + districtRequestDTO.getCountyId(), HttpStatus.NOT_FOUND));

        District entity = District.builder()
                .nome(districtRequestDTO.getNome().trim())
                .county(countyEntity)
                .status(districtRequestDTO.getStatus())
                .build();
        create(entity);

        return getAll().stream()
                .map(district -> modelMapper.map(district, DistrictRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DistrictRequestDTO> updateDistrict(DistrictRequestDTO districtRequestDTO) {
        District entity = getById(districtRequestDTO.getId())
                .orElseThrow( () -> new DomainException("Não existe registro com o código Bairro "
                        + districtRequestDTO.getId(), HttpStatus.NOT_FOUND));

        County countyEntity = countyService.getById(districtRequestDTO.getCountyId())
                .orElseThrow( () -> new DomainException("Não existe registro com o código Municipio "
                        + districtRequestDTO.getCountyId(), HttpStatus.NOT_FOUND));

        entity.setCounty(countyEntity);
        entity.setNome(districtRequestDTO.getNome().trim());
        entity.setStatus(districtRequestDTO.getStatus());
        update(entity);

        return getAll().stream()
                .map(district -> modelMapper.map(district, DistrictRequestDTO.class))
                .collect(Collectors.toList());
    }


    private void validateDistrict(District district) {

        if (repository.existsByNomeAndCounty(district.getNome(), district.getCounty())){
            throw new DomainException("Já existe um bairro com o nome " + district.getNome()
                    + " cadastrado na cidade " + district.getCounty().getNome()
                    + ", não é possível ter dois bairros com mesmo nome na mesma cidade.");
        }

        CheckValidate.checkRequiredName(district.getNome());

        CheckValidate.checkNameLength(district.getNome(), 256);

        CheckValidate.checkRequiredStatus(district.getStatus());
    }
}
