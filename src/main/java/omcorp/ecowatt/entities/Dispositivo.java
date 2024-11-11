package omcorp.ecowatt.entities;

import jakarta.persistence.*;
import lombok.*;
import omcorp.ecowatt.dto.DispositivoResponse;

import java.util.List;
import java.util.UUID;

@Table(name = "DISPOSITIVOS")
@Entity
@Builder
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_dispositivo")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "consumos")
    @OneToMany(mappedBy = "dispositivo")
    private List<Consumo> consumos;

    @Column(name = "alertas")
    @OneToMany(mappedBy = "dispositivo")
    private List<Alerta> alertas;

    public DispositivoResponse toResponse() {
        var listaAlertasResponse = this.alertas
                .stream()
                .map(Alerta::toResponse)
                .toList();

        var listaConsumosResponse = this.consumos
                .stream()
                .map(Consumo::toResponse)
                .toList();

        return DispositivoResponse.builder()
                .id(this.getId())
                .nome(this.getNome())
                .alertas(listaAlertasResponse)
                .consumos(listaConsumosResponse)
                .build();
    }
}
