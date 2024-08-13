package test.api.validadores;

import org.springframework.stereotype.Component;
import test.api.model.Ticket;
import test.api.repository.ValidarTicketRepository;

import java.time.Duration;
@Component
public class ValidarDataAbFe implements ValidarTicketRepository {
    public void validar(Ticket ticket) {
        if (Duration.between(ticket.getOpeningDate().toInstant(), ticket.getClosingDate().toInstant()).toDays() < 1) {
            throw new ValidacaoException("Ticket não pode ser criado nesta data");
        }
    }
}
