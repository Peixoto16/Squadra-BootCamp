package israel.squadra.bootcamp.service.impl;

import israel.squadra.bootcamp.model.Address;
import israel.squadra.bootcamp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {


    @Override
    public void create(List<Address> address) {

    }

    @Override
    public List<Address> findAll(List<Integer> codigos) {
        return null;
    }

    @Override
    public void delete(List<Address> addressToRemove) {

    }
}
