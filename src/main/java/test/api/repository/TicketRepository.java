package test.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import test.api.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    // Consulta JPQL para retornar todos os tickets ordenados pelo ID do cliente
    @Query("SELECT t FROM Ticket t ORDER BY t.fkIdClient")
    List<Ticket> findTicketsGroupedByClient();

    // Consulta JPQL para retornar todos os tickets ordenados pelo ID do módulo
    @Query("SELECT t FROM Ticket t ORDER BY t.fkIdModule")
    List<Ticket> findAllTicketsGroupedByModule();

    //Busca por ID
    Optional<Ticket> findById(UUID id);
}
