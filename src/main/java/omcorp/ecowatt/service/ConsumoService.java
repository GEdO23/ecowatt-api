package omcorp.ecowatt.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.ConsumoRequest;
import omcorp.ecowatt.dto.ConsumoResponse;
import omcorp.ecowatt.entities.Consumo;
import omcorp.ecowatt.entities.Dispositivo;
import omcorp.ecowatt.repository.ConsumoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsumoService {

    private final DispositivoService dispositivoService;
    private final ConsumoRepository consumoRepository;

    @Transactional
    public ResponseEntity<ConsumoResponse> createConsumo(@Valid ConsumoRequest request) {
        Dispositivo dispositivo = dispositivoService.getById(request.getIdDispositivo());

        Consumo consumo = request.toEntity(dispositivo);
        consumo.setDataHora(LocalDateTime.now());
        consumo = consumoRepository.save(consumo);

        dispositivo.getConsumos().addFirst(consumo);
        dispositivoService.salvarDispositivo(dispositivo);

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
}
