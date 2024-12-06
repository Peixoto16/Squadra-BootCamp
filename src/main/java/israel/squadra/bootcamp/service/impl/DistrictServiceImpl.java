package israel.squadra.bootcamp.service.impl;

import israel.squadra.bootcamp.controller.exception.DomainException;
import israel.squadra.bootcamp.dto.request.DistrictRequestDTO;
import israel.squadra.bootcamp.model.County;
import israel.squadra.bootcamp.model.District;
import israel.squadra.bootcamp.repository.DistrictRepository;
import israel.squadra.bootcamp.service.excepValidate.CheckValidate;
import israel.squadra.bootcamp.service.CountyService;
import israel.squadra.bootcamp.service.DistrictService;
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
    public Object getAllParamsDistrict(String nome, Integer status, Integer countyId, Integer id) {
        County countyModel = null;

        if(countyId != null) {
            countyModel = countyService.getById(countyId).orElse(County.builder().id(countyId).build());
        }

        District select = District.builder()
                .nome(nome)
                .status(status)
                .county(countyModel)
                .id(id)
                .build();

        List<District> selectDistrict = repository.findAll(Example.of(select));

        if (isDistrictSelectBy(select) && !selectDistrict.isEmpty()) {
            return selectDistrict.stream().findFirst().map(district -> modelMapper.map(district, DistrictRequestDTO.class));
        }

        return selectDistrict.stream()
                .map(district -> modelMapper.map(district, DistrictRequestDTO.class))
                .collect(Collectors.toList());
    }

    private boolean isDistrictSelectBy(District select) {
        return Objects.nonNull(select.getId());
    }

    @Override
    public List<DistrictRequestDTO> createDistrict(DistrictRequestDTO districtRequestDTO) {

        County countyModel = countyService.getById(districtRequestDTO.getCountyId())
                .orElseThrow(() -> new DomainException("Não existe registro com o código Município "
                        + districtRequestDTO.getCountyId(), HttpStatus.NOT_FOUND));

        District model = District.builder()
                .nome(districtRequestDTO.getNome().trim())
                .county(countyModel)
                .status(districtRequestDTO.getStatus())
                .build();
        create(model);

        return getAll().stream()
                .map(district -> modelMapper.map(district, DistrictRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DistrictRequestDTO> updateDistrict(DistrictRequestDTO districtRequestDTO) {
        District model = getById(districtRequestDTO.getId())
                .orElseThrow( () -> new DomainException("Não existe registro com o código Bairro "
                        + districtRequestDTO.getId(), HttpStatus.NOT_FOUND));

        County countyModel = countyService.getById(districtRequestDTO.getCountyId())
                .orElseThrow( () -> new DomainException("Não existe registro com o código Municipio "
                        + districtRequestDTO.getCountyId(), HttpStatus.NOT_FOUND));

        model.setCounty(countyModel);
        model.setNome(districtRequestDTO.getNome().trim());
        model.setStatus(districtRequestDTO.getStatus());
        update(model);

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

        CheckValidate.checkNameLength(district.getNome(), 256);

        CheckValidate.checkRequiredStatus(district.getStatus());
    }
}
