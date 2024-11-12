package omcorp.ecowatt.dto.consumo;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import omcorp.ecowatt.entities.Consumo;
import omcorp.ecowatt.entities.Dispositivo;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumoRequest {

    @NotNull(message = "O Consumo não pode ser nulo.")
    private BigDecimal consumo;

    @NotNull(message = "O id do dispositivo não pode ser nulo.")
    private UUID idDispositivo;

    public Consumo toEntity(Dispositivo dispositivo) {
        return Consumo.builder()
                .consumo(this.consumo)
                .dispositivo(dispositivo)
                .build();
    }
}
