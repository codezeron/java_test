package test.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.api.model.Cliente;
import test.api.model.Modulo;
import test.api.model.Ticket;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketDetailService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModuloService moduloService;


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

    public List<Ticket> listarTicketsPorMes(int mes) {
        List<Ticket> tickets = listarTicketsCompleta();
        return tickets.stream()
                .filter(ticket -> ticket.getOpeningDate().toLocalDateTime().getMonthValue()== mes)
                .collect(Collectors.toList());
    }
}