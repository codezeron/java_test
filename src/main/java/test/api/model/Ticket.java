package test.api.model;



import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;


@Entity

@Data
@Table(name = "TICKET")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Gera UUID automaticamente
    private UUID id;

    @Column(name = "TITLE")  // Nome da coluna no banco de dados
    private String titulo;

    @Column(name = "FK_ID_CLIENT")  // Nome da coluna no banco de dados
    private Integer fkIdClient;

    @Column(name = "OPENING_DATE")  // Nome da coluna no banco de dados
    private Timestamp openingDate;

    @Column(name = "CLOSING_DATE")  // Nome da coluna no banco de dados
    private Timestamp closingDate;

    @Column(name = "FK_ID_MODULE")  // Nome da coluna no banco de dados
    private Integer fkIdModule;

    // Métodos set
    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setFkIdClient(Integer fkIdClient) {
        this.fkIdClient = fkIdClient;
    }

    public void setOpeningDate(Timestamp openingDate) {
        this.openingDate = openingDate;
    }

    public void setClosingDate(Timestamp closingDate) {
        this.closingDate = closingDate;
    }

    public void setFkIdModule(Integer fkIdModule) {
        this.fkIdModule = fkIdModule;
    }
}
