package omcorp.ecowatt.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @OneToMany(mappedBy = "dispositivos")
    private List<Consumo> consumos;

    @Column(name = "alertas")
    @OneToMany(mappedBy = "dispositivos")
    private List<Alerta> alertas;
}
