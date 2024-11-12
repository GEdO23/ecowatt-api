package omcorp.ecowatt.dto.consumo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumoResponse {
    private UUID id;
    private BigDecimal consumo;
    private LocalDateTime dataHora;
    private UUID idDispositivo;
}
