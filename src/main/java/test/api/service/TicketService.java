package test.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.api.model.Ticket;
import test.api.repository.TicketRepository;
import test.api.utils.DateUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Método para buscar ticket por ID
    public Optional<Ticket> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return ticketRepository.findById(id);
    }

    // Método para listar todos os tickets
    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    // Método para salvar um ticket
    public Ticket salvarTicket(Ticket ticket) {

        ticket.setOpeningDate(Timestamp.valueOf(LocalDateTime.now()));
        ticket.setClosingDate(Timestamp.valueOf(LocalDateTime.now()));
        //sobrescrevi as datas para o horario de brasilia
        //estava dando erro de timezone
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

    // Método para listar tickets por mês
    public List<Ticket> listarTicketsPorMes(int mes) {
        // Chama o método findTicketsByMonth do repositório para obter todos os tickets do mês
        return ticketRepository.findTicketsByMonth(mes);
    }
    // Método para deletar um ticket por ID (usado apenas para teste)
    public void deletarTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
