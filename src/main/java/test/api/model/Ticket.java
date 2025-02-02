package test.api.model;



import java.sql.Timestamp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
@Table(name = "TICKET")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Gera UUID automaticamente
    private Long id;

    @Column(name = "TITLE")  // Nome da coluna no banco de dados
    private String titulo;

    @NotNull
    @Column(name = "FK_ID_CLIENT")  // Nome da coluna no banco de dados
    private Integer fkIdClient;

    @NotNull
    @Column(name = "OPENING_DATE")  // Nome da coluna no banco de dados
    private Timestamp openingDate;

    @NotNull
    @Column(name = "CLOSING_DATE")  // Nome da coluna no banco de dados
    private Timestamp closingDate;

    @NotNull
    @Column(name = "FK_ID_MODULE")  // Nome da coluna no banco de dados
    private Integer fkIdModule;

    @Transient //nao persiste no banco de dados, apenas modificar o objeto
    private String clienteName;

    @Transient
    private String moduloName;
}
