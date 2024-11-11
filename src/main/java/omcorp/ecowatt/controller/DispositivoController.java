package omcorp.ecowatt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.DispositivoRequest;
import omcorp.ecowatt.dto.DispositivoResponse;
import omcorp.ecowatt.service.DispositivoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DispositivoController {

    private final DispositivoService service;

    @PostMapping("/dispositivo")
    public ResponseEntity<DispositivoResponse> createDispositivo(@RequestBody @Valid DispositivoRequest request) {
        return service.createDispositivo(request);
    }
}
