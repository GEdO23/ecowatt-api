package omcorp.ecowatt.repository;

import omcorp.ecowatt.entities.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, UUID> {
}
