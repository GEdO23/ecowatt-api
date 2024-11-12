package omcorp.ecowatt.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.AtualizarDispositivoRequest;
import omcorp.ecowatt.dto.DispositivoRequest;
import omcorp.ecowatt.dto.DispositivoResponse;
import omcorp.ecowatt.dto.ListDispositivoResponse;
import omcorp.ecowatt.entities.Alerta;
import omcorp.ecowatt.entities.Consumo;
import omcorp.ecowatt.entities.Dispositivo;
import omcorp.ecowatt.repository.DispositivoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DispositivoService {

    private final DispositivoRepository repository;

    @Transactional
    public ResponseEntity<DispositivoResponse> createDispositivo(DispositivoRequest request) {
        Dispositivo dispositivo = Dispositivo.builder()
                .nome(request.getNome())
                .local(request.getLocal())
                .tipo(request.getTipo())
                .limiteConsumo(request.getLimiteConsumo())
                .alertas(new ArrayList<Alerta>())
                .consumos(new ArrayList<Consumo>())
                .build();

        dispositivo = repository.save(dispositivo);

        DispositivoResponse response = DispositivoResponse.builder()
                .id(dispositivo.getId())
                .nome(dispositivo.getNome())
                .local(dispositivo.getLocal())
                .tipo(dispositivo.getTipo())
                .limiteConsumo(dispositivo.getLimiteConsumo())
                .alertas(dispositivo.toResponse().getAlertas())
                .consumos(dispositivo.toResponse().getConsumos())
                .build();

        var uriBuilder = UriComponentsBuilder.newInstance();

        var uri = uriBuilder.path("/dispositivo/{id}").buildAndExpand(dispositivo.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    public Dispositivo getById(UUID idDispositivo) {
        return repository.getReferenceById(idDispositivo);
    }

    public void salvarDispositivo(Dispositivo dispositivo) {
        repository.save(dispositivo);
    }

    public ResponseEntity<List<ListDispositivoResponse>> getDispositivos() {
        List<ListDispositivoResponse> dispositivos = repository.findAll().stream().map(Dispositivo::toListResponse).toList();
        return ResponseEntity.ok(dispositivos);
    }

    public ResponseEntity deleteDispositivo(UUID id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }

    }

    @Transactional
    public ResponseEntity<DispositivoResponse> atualizarDispositivo(@Valid AtualizarDispositivoRequest request) {
        var dispositivo = repository.getReferenceById(request.getId());
        dispositivo = dispositivo.toBuilder()
                .nome(request.getNome())
                .local(request.getLocal())
                .tipo(request.getTipo())
                .limiteConsumo(request.getLimiteConsumo())
                .build();

        dispositivo = repository.save(dispositivo);

        return ResponseEntity.ok(dispositivo.toResponse());
    }
}
