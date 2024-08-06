package test.api.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MODULE")
public class Modulo {
    @Id
    private Integer id;

    private String name;

    @Transient
    private List<Ticket> tickets;
}
