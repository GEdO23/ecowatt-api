package omcorp.ecowatt.entities;

import jakarta.persistence.*;
import lombok.*;
import omcorp.ecowatt.dto.alerta.AlertaResponse;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "ALERTAS")
@Entity
@Builder
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_dispositivo", nullable = false)
    private Dispositivo dispositivo;

    public AlertaResponse toResponse() {
        return AlertaResponse.builder()
                .id(this.id)
                .mensagem(this.mensagem)
                .idDispositivo(this.dispositivo.getId())
                .dataHora(this.dataHora)
                .build();
    }
}
