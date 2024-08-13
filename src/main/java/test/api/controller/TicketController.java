package test.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.api.model.Ticket;
import test.api.repository.ValidarTicketRepository;
import test.api.service.TicketDetailService;
import test.api.service.TicketService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketDetailService ticketDetailService;

    @Autowired
    private List<ValidarTicketRepository> validarTicketRepositoryList;


    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable Long id) {
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

    @GetMapping("/listarTicketsCompleta")
    public List<Ticket> listarTicketsCompleta() {
        return ticketDetailService.listarTicketsCompleta();
    }

    @GetMapping("/por-clientes")
    public Map<Integer, List<Ticket>> listarTicketsAgrupadosPorCliente() {
        return ticketService.listarTicketsAgrupadosPorCliente();
    }

    @GetMapping("/por-modulos")
    public Map<Integer, List<Ticket>> listarTicketsAgrupadosPorModulo() {
        return ticketService.listarTicketsAgrupadosPorModulo();
    }

    @GetMapping("/ticket-por-mes/{mes}")
    public List<Ticket> listarTicketsPorMes(@PathVariable Integer mes) {
        return ticketDetailService.listarTicketsPorMes(mes);
    }

    @PostMapping
    public Ticket salvarTicket(@Valid @RequestBody Ticket ticket) {
        validarTicketRepositoryList.forEach(v -> v.validar(ticket));
        return ticketService.salvarTicket(ticket);
    }
}
