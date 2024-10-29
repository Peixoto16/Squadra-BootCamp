package israel.squadra_bootcamp.service;

import israel.squadra_bootcamp.model.Uf;

import java.util.List;

public interface UfService {

    void create(Uf uf);

    List<Uf> getUfs();

}
