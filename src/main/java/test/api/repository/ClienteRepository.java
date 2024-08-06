package test.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.api.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
