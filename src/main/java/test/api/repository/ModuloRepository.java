package test.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.api.model.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
}
