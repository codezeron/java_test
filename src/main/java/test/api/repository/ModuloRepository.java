package test.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import test.api.model.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
}
