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
import java.util.Optional;

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
}
