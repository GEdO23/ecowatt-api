package omcorp.ecowatt.entities;

import jakarta.persistence.*;
import lombok.*;
import omcorp.ecowatt.dto.DispositivoResponse;
import omcorp.ecowatt.dto.ListDispositivoResponse;

import java.math.BigDecimal;
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

    @Column(name = "local")
    private String local;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "limite_consumo")
    private BigDecimal limiteConsumo;

    @Column(name = "consumos")
    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL)
    private List<Consumo> consumos;

    @Column(name = "alertas")
    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL)
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
                .tipo(this.getTipo())
                .local(this.getLocal())
                .limiteConsumo(this.getLimiteConsumo())
                .alertas(listaAlertasResponse)
                .consumos(listaConsumosResponse)
                .build();
    }

    public ListDispositivoResponse toListResponse() {

        return ListDispositivoResponse.builder()
                .id(this.getId())
                .nome(this.getNome())
                .tipo(this.getTipo())
                .local(this.getLocal())
                .limiteConsumo(this.getLimiteConsumo())
                .build();
    }
}
