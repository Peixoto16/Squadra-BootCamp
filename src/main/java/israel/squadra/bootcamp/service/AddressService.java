package israel.squadra.bootcamp.service;

import israel.squadra.bootcamp.model.Address;

import java.util.List;

public interface AddressService {
    void create(List<Address> address);

    List<Address> findAll(List<Integer> listAdd);

    void delete(List<Address> addressToRemove);
}
