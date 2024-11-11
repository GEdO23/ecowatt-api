package omcorp.ecowatt.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omcorp.ecowatt.entities.Alerta;
import omcorp.ecowatt.entities.Dispositivo;
import omcorp.ecowatt.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;

    @Transactional
    public void gerarAlerta(Dispositivo dispositivo) {
        String mensagem = "O Limite de " + dispositivo.getLimiteConsumo().toString() +
                "W do dispositivo " + dispositivo.getNome() +
                " foi excedido.";

        Alerta alerta = Alerta.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(mensagem)
                .dispositivo(dispositivo)
                .build();

        alertaRepository.save(alerta);
        log.info("Um alerta foi gerado: " + mensagem);
    }
}
