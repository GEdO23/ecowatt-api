package omcorp.ecowatt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlertaResponse {

    private UUID id;

    private String mensagem;

    private LocalDateTime dataHora;

    private UUID idDispositivo;
}
