package test.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.api.model.Modulo;
import test.api.repository.ModuloRepository;

import java.util.List;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    public List<Modulo> listarModulos() {
        return moduloRepository.findAll();
    }

    public Modulo salvarModulo(Modulo modulo) {
        return moduloRepository.save(modulo);
    }
}
