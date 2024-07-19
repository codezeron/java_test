package test.api.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENT")
public class Cliente {
    @Id
    private Integer id;
    private String name;

}
