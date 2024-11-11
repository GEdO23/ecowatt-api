package omcorp.ecowatt.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.DispositivoRequest;
import omcorp.ecowatt.dto.DispositivoResponse;
import omcorp.ecowatt.entities.Alerta;
import omcorp.ecowatt.entities.Consumo;
import omcorp.ecowatt.entities.Dispositivo;
import omcorp.ecowatt.repository.DispositivoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class DispositivoService {

    private final DispositivoRepository repository;
    private final AlertaService alertaService;

    @Transactional
    public ResponseEntity<DispositivoResponse> createDispositivo(DispositivoRequest request) {
        Dispositivo dispositivo = Dispositivo.builder()
                .nome(request.getNome())
                .alertas(new ArrayList<Alerta>())
                .consumos(new ArrayList<Consumo>())
                .build();

        dispositivo = repository.save(dispositivo);

        DispositivoResponse response = DispositivoResponse.builder()
                .id(dispositivo.getId())
                .nome(dispositivo.getNome())
                .alertas(dispositivo.toResponse().getAlertas())
                .consumos(dispositivo.toResponse().getConsumos())
                .build();

        var uriBuilder = UriComponentsBuilder.newInstance();

        var uri = uriBuilder.path("/dispositivo/{id}").buildAndExpand(dispositivo.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
