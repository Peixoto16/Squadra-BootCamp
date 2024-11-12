package israel.squadra.bootcamp.service.impl;

import israel.squadra.bootcamp.model.Address;
import israel.squadra.bootcamp.repository.AddressRepository;
import israel.squadra.bootcamp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    @Override
    public void create(List<Address> address) {
        repository.saveAll(address);
    }

    @Override
    public List<Address> findAll(List<Integer> listAdd) {

        return repository.findAll();
    }

    @Override
    public void delete(List<Address> address) {

        address.forEach(repository::delete);
    }
}
