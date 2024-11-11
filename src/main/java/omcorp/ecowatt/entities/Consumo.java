package omcorp.ecowatt.entities;

import jakarta.persistence.*;
import lombok.*;
import omcorp.ecowatt.dto.ConsumoResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "CONSUMOS")
@Entity
@Builder
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "consumo")
    private BigDecimal consumo;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_dispositivo", nullable = false)
    private Dispositivo dispositivo;

    public ConsumoResponse toResponse() {
        return ConsumoResponse.builder()
                .id(this.id)
                .consumo(this.consumo)
                .dataHora(this.dataHora)
                .idDispositivo(this.dispositivo.getId())
                .build();
    }
}
