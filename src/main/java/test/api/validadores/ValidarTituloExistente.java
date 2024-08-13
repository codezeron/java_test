package test.api.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.api.model.Ticket;
import test.api.repository.TicketRepository;
import test.api.repository.ValidarTicketRepository;

@Component
public class ValidarTituloExistente implements ValidarTicketRepository {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void validar(Ticket ticket) {
        if (ticketRepository.existsByTitulo(ticket.getTitulo())) {
            throw new ValidacaoException("Título do ticket já existe");
        }
    }
}