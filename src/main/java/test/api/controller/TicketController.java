package test.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.api.model.Cliente;
import test.api.model.Modulo;
import test.api.model.Ticket;
import test.api.service.TicketService;
import test.api.service.ClienteService;
import test.api.service.ModuloService;

import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final ClienteService clienteService;
    private final ModuloService moduloService;

    //para evitar a injeção de dependencia, usamos o @Autowired em cima do construtor
    @Autowired
    public TicketController(TicketService ticketService, ClienteService clienteService, ModuloService moduloService) {
        this.ticketService = ticketService;
        this.clienteService = clienteService;
        this.moduloService = moduloService;
    }

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
        List<Ticket> tickets = ticketService.listarTickets();
        List<Cliente> clients = clienteService.listarClientes();
        List<Modulo> modulos = moduloService.listarModulos();

        return tickets.stream().peek(ticket -> {
            ticket.setClienteName(clients.stream()
                    .filter(c -> c.getId().equals(ticket.getFkIdClient()))
                    .map(Cliente::getName)
                    .findFirst()
                    .orElse(null));
            ticket.setModuloName(modulos.stream()
                    .filter(m -> m.getId().equals(ticket.getFkIdModule()))
                    .map(Modulo::getName)
                    .findFirst()
                    .orElse(null));
        }).collect(Collectors.toList());
    }

    @GetMapping("/por-clientes")
    public Map<Integer, List<Ticket>> listarTicketsAgrupadosPorCliente() {
        return ticketService.listarTicketsAgrupadosPorCliente();
    }

    @GetMapping("/por-modulos")
    public Map<Integer, List<Ticket>> listarTicketsAgrupadosPorModulo() {
        return ticketService.listarTicketsAgrupadosPorModulo();
    }

    @PostMapping
    public Ticket salvarTicket(@RequestBody Ticket ticket) {
        return ticketService.salvarTicket(ticket);
    }
}
