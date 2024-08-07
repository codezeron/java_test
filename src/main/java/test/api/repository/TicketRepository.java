package test.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import test.api.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;

import java.util.List;
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Consulta JPQL para retornar todos os tickets ordenados pelo ID do cliente
    @Query("SELECT t FROM Ticket t ORDER BY t.fkIdClient")
    List<Ticket> findTicketsGroupedByClient();

    // Consulta JPQL para retornar todos os tickets ordenados pelo ID do módulo
    @Query("SELECT t FROM Ticket t ORDER BY t.fkIdModule")
    List<Ticket> findAllTicketsGroupedByModule();

    @Query("SELECT t from Ticket t where EXTRACT(MONTH FROM t.openingDate) = :month AND EXTRACT(MONTH FROM t.closingDate) = :month")
    List<Ticket> findTicketsByMonth(@Param(value = "month") int month);
    //Busca por ID
    @Override
    Optional<Ticket> findById( Long id);
}
