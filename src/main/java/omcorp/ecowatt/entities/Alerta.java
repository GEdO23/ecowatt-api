package omcorp.ecowatt.entities;

import jakarta.persistence.*;
import lombok.*;
import omcorp.ecowatt.dto.AlertaResponse;

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

    @ManyToOne
    @JoinColumn(name = "id_dispositivo", nullable = false)
    private Dispositivo dispositivo;

    public AlertaResponse toResponse() {
        return AlertaResponse.builder()
                .id(this.id)
                .mensagem(this.mensagem)
                .idDispositivo(this.dispositivo.getId())
                .build();
    }
}
