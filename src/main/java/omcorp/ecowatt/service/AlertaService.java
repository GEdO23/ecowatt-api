package omcorp.ecowatt.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omcorp.ecowatt.dto.AlertaResponse;
import omcorp.ecowatt.entities.Alerta;
import omcorp.ecowatt.entities.Dispositivo;
import omcorp.ecowatt.repository.AlertaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final DispositivoService dispositivoService;

    @Transactional
    public void gerarAlerta(Dispositivo dispositivo, BigDecimal consumo) {
        String mensagem = "O Limite de " + dispositivo.getLimiteConsumo().toString() +
                "W do dispositivo " + dispositivo.getNome() +
                " foi excedido: " + consumo.toString() + "W";

        Alerta alerta = Alerta.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(mensagem)
                .dispositivo(dispositivo)
                .build();

        alerta = alertaRepository.save(alerta);
        log.info("Um alerta foi gerado: " + mensagem);
    }


    public ResponseEntity<List<AlertaResponse>> getAlertasByDispositivos(UUID id) {
        Dispositivo dispositivo = dispositivoService.getById(id);
        List<Alerta> alertas = dispositivo.getAlertas();

        List<AlertaResponse> alertaResponseList = alertas.stream()
                .map(Alerta::toResponse)
                .toList();

        return ResponseEntity.ok(alertaResponseList);
    }
}
