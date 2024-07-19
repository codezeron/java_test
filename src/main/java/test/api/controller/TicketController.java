package test.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.api.model.Ticket;
import test.api.service.TicketService;
import java.util.Map;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable UUID id) {
        // Chama o método buscarTicketPorId do serviço para obter um ticket pelo ID
        Optional<Ticket> ticket = ticketService.findById(id);
        // Verifica se o ticket foi encontrado
        if (ticket.isPresent()) {
            // Retorna o ticket encontrado com status ok
            return ResponseEntity.ok().body(ticket.get());
        } else {
            // Retorna status Not Found caso o ticket não seja encontrado
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Ticket> listarTickets() {
        return ticketService.listarTickets();
    }

    @PostMapping
    public Ticket salvarTicket(@RequestBody Ticket ticket) {
        return ticketService.salvarTicket(ticket);
    }

    @GetMapping("/por-clientes")
    public Map<Integer, List<Ticket>> listarTicketsAgrupadosPorCliente() {
        return ticketService.listarTicketsAgrupadosPorCliente();
    }

    @GetMapping("/por-modulos")
    public Map<Integer, List<Ticket>> listarTicketsAgrupadosPorModulo() {
        return ticketService.listarTicketsAgrupadosPorModulo();
    }

}
