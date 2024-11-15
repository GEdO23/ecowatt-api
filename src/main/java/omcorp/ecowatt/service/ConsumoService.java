package omcorp.ecowatt.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.consumo.ConsumoRequest;
import omcorp.ecowatt.dto.consumo.ConsumoResponse;
import omcorp.ecowatt.dto.consumo.RelatorioResponse;
import omcorp.ecowatt.entities.Consumo;
import omcorp.ecowatt.entities.Dispositivo;
import omcorp.ecowatt.repository.ConsumoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsumoService {

    private final DispositivoService dispositivoService;
    private final AlertaService alertaService;
    private final ConsumoRepository consumoRepository;

    @Transactional
    public ResponseEntity<ConsumoResponse> createConsumo(@Valid ConsumoRequest request) {
        Dispositivo dispositivo = dispositivoService.getById(request.getIdDispositivo());

        Consumo consumo = request.toEntity(dispositivo);
        consumo.setDataHora(LocalDateTime.now());
        consumo = consumoRepository.save(consumo);

        dispositivo.getConsumos().addFirst(consumo);
        dispositivoService.salvarDispositivo(dispositivo);

        if (consumo.getConsumo().compareTo(dispositivo.getLimiteConsumo()) >= 0) {
            alertaService.gerarAlerta(dispositivo, consumo.getConsumo());
        }

        var uriBuilder = UriComponentsBuilder.newInstance();
        var uri = uriBuilder.path("/consumo/{id}").buildAndExpand(dispositivo.getId()).toUri();

        return ResponseEntity.created(uri).body(consumo.toResponse());
    }

    public ResponseEntity<List<ConsumoResponse>> getConsumosByDispositivo(UUID id) {
        Dispositivo dispositivo = dispositivoService.getById(id);
        List<Consumo> consumos = dispositivo.getConsumos();

        List<ConsumoResponse> consumoResponseList =  consumos.stream()
                .map(Consumo::toResponse)
                .toList();

        return ResponseEntity.ok(consumoResponseList);

    }

    public ResponseEntity<RelatorioResponse> getRelatorio(@RequestParam LocalDate data) {
        List<Consumo> consumos = consumoRepository.findAll();
        consumos = consumos.stream()
                .filter(consumo -> consumo.getDataHora().toLocalDate().equals(data))
                .toList();

        BigDecimal totalConsumo = consumos.stream()
                .map(Consumo::getConsumo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String mensagem = "O Consumo do dia " + data.toString() + " foi de " + totalConsumo + "W.";

        RelatorioResponse relatorio = RelatorioResponse.builder()
                .totalConsumo(totalConsumo)
                .mensagem(mensagem)
                .data(data)
                .build();

        return ResponseEntity.ok(relatorio);
    }
}
