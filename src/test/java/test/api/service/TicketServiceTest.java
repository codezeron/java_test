//package test.api.service;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import test.api.model.Ticket;
//import test.api.repository.TicketRepository;
//
//import java.sql.Timestamp;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class TicketServiceTest {
//
//    @Autowired
//    private TicketService ticketService;
//
//    @Autowired
//    private TicketRepository ticketRepository;
//
//    private Ticket ticket;
//
//    @BeforeEach
//    public void setup() {
//        // Criação do ticket para teste
//        ticket = new Ticket();
//        ticket.setId(UUID.randomUUID()); // Gera um UUID aleatório
//        ticket.setTitulo("Teste de Ticket");
//        ticket.setFkIdClient(1);
//        Timestamp openingDate = Timestamp.valueOf("2024-01-01 10:00:00");
//        Timestamp closingDate = Timestamp.valueOf("2024-01-02 18:00:00");
//        ticket.setOpeningDate(openingDate);
//        ticket.setClosingDate(closingDate);
//        ticket.setFkIdModule(1);
//
//        // Salva o ticket no banco de dados para o teste
//        ticketRepository.save(ticket);
//
//        // Verificação para garantir que o ticket foi salvo
//        Optional<Ticket> savedTicket = ticketRepository.findById(ticket.getId());
//        assertTrue(savedTicket.isPresent(), "Saved ticket should be present in the repository");
//    }
//
//    @Test
//    @Transactional
//    public void testCriarEBuscarTicket() {
//        // Busca o ticket pelo ID
//        Optional<Ticket> foundTicketOptional = ticketService.findById(ticket.getId());
//
//        // Verifica se o ticket foi encontrado e se os dados estão corretos
//        assertTrue(foundTicketOptional.isPresent(), "Ticket should be present");
//        Ticket foundTicket = foundTicketOptional.get();
//
//        // Verifica cada campo do ticket
//        assertNotNull(foundTicket.getId(), "ID should not be null");
//        assertEquals(ticket.getTitulo(), foundTicket.getTitulo(), "Ticket title should match");
//        assertEquals(ticket.getFkIdClient(), foundTicket.getFkIdClient(), "Client ID should match");
//        assertEquals(ticket.getOpeningDate(), foundTicket.getOpeningDate(), "Opening date should match");
//        assertEquals(ticket.getClosingDate(), foundTicket.getClosingDate(), "Closing date should match");
//        assertEquals(ticket.getFkIdModule(), foundTicket.getFkIdModule(), "Module ID should match");
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // Remove o ticket do banco de dados após o teste
//        ticketRepository.deleteById(ticket.getId());
//    }
//}
