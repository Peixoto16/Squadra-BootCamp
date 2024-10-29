package israel.squadra_bootcamp.service.impl;

import israel.squadra_bootcamp.model.Uf;
import israel.squadra_bootcamp.repository.UfRepository;
import israel.squadra_bootcamp.service.UfService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UfServiceImpl implements UfService {

    private final UfRepository repository;

    @Override
    public void create(Uf uf) {

        repository.save(uf);
    }
    @Override
    public List<Uf> getUfs() {
        return repository.findAll();
    }





}
