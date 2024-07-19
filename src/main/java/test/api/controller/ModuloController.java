package test.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.api.model.Modulo;
import test.api.service.ModuloService;

import java.util.List;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @GetMapping
    public List<Modulo> listarModulos() {
        return moduloService.listarModulos();
    }

    @PostMapping
    public Modulo salvarModulo(@RequestBody Modulo modulo) {
        return moduloService.salvarModulo(modulo);
    }
}
