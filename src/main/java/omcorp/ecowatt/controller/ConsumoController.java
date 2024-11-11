package omcorp.ecowatt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.ConsumoRequest;
import omcorp.ecowatt.dto.ConsumoResponse;
import omcorp.ecowatt.service.ConsumoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConsumoController {

    private final ConsumoService consumoService;

    @PostMapping("/consumo")
    public ResponseEntity<ConsumoResponse> createConsumo(@RequestBody @Valid ConsumoRequest request) {
        return consumoService.createConsumo(request);
    }
}
