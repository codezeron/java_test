package test.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.api.model.Ticket;
import test.api.repository.TicketRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Método para buscar ticket por ID
    public Optional<Ticket> findById(UUID id) {
        return ticketRepository.findById(id);
    }

    // Método para listar todos os tickets
    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    // Método para salvar um ticket
    public Ticket salvarTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // Método para listar tickets agrupados por cliente
    public Map<Integer, List<Ticket>> listarTicketsAgrupadosPorCliente() {
        // Chama o método findAllTicketsGroupedByClient do repositório para obter todos os tickets
        List<Ticket> tickets = ticketRepository.findTicketsGroupedByClient();

        // Agrupa os tickets pelo ID do cliente usando a API de streams do Java
        return tickets.stream().collect(Collectors.groupingBy(Ticket::getFkIdClient));
    }

    // Método para listar tickets agrupados por módulo
    public Map<Integer, List<Ticket>> listarTicketsAgrupadosPorModulo() {
        // Chama o método findAllTicketsGroupedByModule do repositório para obter todos os tickets
        List<Ticket> tickets = ticketRepository.findAllTicketsGroupedByModule();

        // Agrupa os tickets pelo ID do módulo usando a API de streams do Java
        return tickets.stream().collect(Collectors.groupingBy(Ticket::getFkIdModule));
    }

    // Método para deletar um ticket por ID (usado apenas para teste)
    public void deletarTicket(UUID id) {
        ticketRepository.deleteById(id);
    }
}
