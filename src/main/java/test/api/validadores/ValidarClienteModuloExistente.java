package test.api.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.api.model.Ticket;
import test.api.repository.ClienteRepository;
import test.api.repository.ModuloRepository;
import test.api.repository.ValidarTicketRepository;

@Component
public class ValidarClienteModuloExistente implements ValidarTicketRepository {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    public void validar(Ticket ticket) {
        if (!clienteRepository.existsById(ticket.getFkIdClient())) {
            throw new ValidacaoException("Cliente não existe");
        }
        if (!moduloRepository.existsById(ticket.getFkIdModule())) {
            throw new ValidacaoException("Modulo não existe");
        }
    }
}