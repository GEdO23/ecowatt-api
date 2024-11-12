package omcorp.ecowatt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.AtualizarDispositivoRequest;
import omcorp.ecowatt.dto.DispositivoRequest;
import omcorp.ecowatt.dto.DispositivoResponse;
import omcorp.ecowatt.dto.ListDispositivoResponse;
import omcorp.ecowatt.service.DispositivoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dispositivo")
public class DispositivoController {

    private final DispositivoService service;

    @PostMapping
    public ResponseEntity<DispositivoResponse> createDispositivo(@RequestBody @Valid DispositivoRequest request) {
        return service.createDispositivo(request);
    }

    @GetMapping
    public ResponseEntity<List<ListDispositivoResponse>> getDispositivos() {
        return service.getDispositivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoResponse> getDispositivoById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id).toResponse());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDispositivo(@PathVariable UUID id) {
        return service.deleteDispositivo(id);
    }

    @PutMapping
    public ResponseEntity<DispositivoResponse> atualizarDispositivo(@RequestBody @Valid AtualizarDispositivoRequest request) {
        return service.atualizarDispositivo(request);
    }
}
