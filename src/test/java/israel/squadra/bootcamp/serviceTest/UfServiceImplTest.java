package israel.squadra.bootcamp.serviceTest;

import israel.squadra.bootcamp.controller.exception.DomainException;
import israel.squadra.bootcamp.model.Uf;
import israel.squadra.bootcamp.repository.UfRepository;
import israel.squadra.bootcamp.service.impl.UfServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UfServiceImplTest {

    @InjectMocks
    private UfServiceImpl ufService;

    @Mock
    private UfRepository repository;

    private Uf uf;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        uf = new Uf();
        uf.setId(1);
        uf.setSigla("SP");
        uf.setNome("São Paulo");
        uf.setStatus(1);
    }

    @Test
    void testCreateUf() {
        when(repository.existsBySigla(uf.getSigla())).thenReturn(false);
        when(repository.existsByNome(uf.getNome())).thenReturn(false);

        ufService.create(uf);

        verify(repository, times(1)).save(uf);
    }

    @Test
    void testCreateUfWithExistingSigla() {
        when(repository.existsBySigla(uf.getSigla())).thenReturn(true);

        DomainException exception = assertThrows(DomainException.class, () -> {
            ufService.create(uf);
        });

        assertEquals("400 BAD_REQUEST \"Já existe um estado com a sigla " + uf.getSigla()+ ".\"", exception.getMessage());
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(uf));

        List<Uf> result = ufService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(uf, result.get(0));
    }

    @Test
    void testGetById() {
        when(repository.findById(1)).thenReturn(Optional.of(uf));

        Optional<Uf> result = ufService.getById(1);

        assertTrue(result.isPresent());
        assertEquals(uf, result.get());
    }

    @Test
    void testUpdateUf() {
        when(repository.existsBySiglaAndIdNot(uf.getSigla(), uf.getId())).thenReturn(false);
        when(repository.existsByNomeAndIdNot(uf.getNome(), uf.getId())).thenReturn(false);
        when(repository.findById(1)).thenReturn(Optional.of(uf));

        ufService.update(uf);

        verify(repository, times(1)).save(uf);
    }

    @Test
    void testUpdateUfWithExistingSigla() {
        when(repository.existsBySiglaAndIdNot(uf.getSigla(), uf.getId())).thenReturn(true);

        DomainException exception = assertThrows(DomainException.class, () -> {
            ufService.update(uf);
        });

        assertEquals("400 BAD_REQUEST \"Já existe um outro estado com a sigla " + uf.getSigla()+ ".\"", exception.getMessage());
    }

}
