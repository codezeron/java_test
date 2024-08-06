package test.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.api.model.Cliente;
import test.api.model.Modulo;
import test.api.model.Ticket;
import test.api.service.ModuloService;
import test.api.service.TicketService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<Modulo> findById(@PathVariable Integer id) {
        Optional<Modulo> modulo = moduloService.findById(id);
        if (modulo.isPresent()) {
            return ResponseEntity.ok().body(modulo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/modulo-tickets")
    public List<Modulo> listarClientesTickets() {
        List<Ticket> tickets = ticketService.listarTickets();
        List<Modulo> modulos = moduloService.listarModulos();

        modulos = modulos.stream().map(modulo -> {
            List<Ticket> moduloTickets = tickets.stream()
                    .filter(ticket -> ticket.getFkIdClient().equals(modulo.getId()))
                    .collect(Collectors.toList());
            modulo.setTickets(moduloTickets);
            return modulo;
        }).collect(Collectors.toList());
        return modulos;
    }
    @GetMapping
    public List<Modulo> listarModulos() {
        return moduloService.listarModulos();
    }

    @PostMapping
    public Modulo salvarModulo(@RequestBody Modulo modulo) {
        return moduloService.salvarModulo(modulo);
    }
}
