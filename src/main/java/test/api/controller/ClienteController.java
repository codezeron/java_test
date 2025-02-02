package test.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.api.model.Cliente;
import test.api.model.Ticket;
import test.api.service.ClienteService;
import test.api.service.TicketService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final TicketService ticketService;

    //para evitar a injeção de dependencia, usamos o @Autowired em cima do construtor
    @Autowired
    public ClienteController(ClienteService clienteService, TicketService ticketService) {
        this.clienteService = clienteService;
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok().body(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client-tickets")
    public List<Cliente> listarClientesTickets() {
        List<Ticket> tickets = ticketService.listarTickets();
        List<Cliente> clients = clienteService.listarClientes();

        clients = clients.stream().peek(client -> {
            List<Ticket> clientTickets = tickets.stream()
                    .filter(ticket -> ticket.getFkIdClient().equals(client.getId()))
                    .collect(Collectors.toList());
            client.setTickets(clientTickets);
        }).collect(Collectors.toList());
        return clients;
    }

    @PostMapping
    public Cliente salvarCliente(@RequestBody Cliente cliente) {
        return clienteService.salvarCliente(cliente);
    }
}
