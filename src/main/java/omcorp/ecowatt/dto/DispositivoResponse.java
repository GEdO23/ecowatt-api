package omcorp.ecowatt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispositivoResponse {

    private UUID id;

    private String nome;

    private List<ConsumoResponse> consumos;

    private List<AlertaResponse> alertas;
}
