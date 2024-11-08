package omcorp.ecowatt.repository;

import omcorp.ecowatt.entities.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, UUID> {
}
