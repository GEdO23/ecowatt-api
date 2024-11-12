package omcorp.ecowatt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.consumo.ConsumoRequest;
import omcorp.ecowatt.dto.consumo.ConsumoResponse;
import omcorp.ecowatt.dto.consumo.RelatorioResponse;
import omcorp.ecowatt.service.ConsumoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consumo")
public class ConsumoController {

    private final ConsumoService consumoService;

    @PostMapping
    public ResponseEntity<ConsumoResponse> createConsumo(@RequestBody @Valid ConsumoRequest request) {
        return consumoService.createConsumo(request);
    }

    @GetMapping("/dispositivo/{id}")
    public ResponseEntity<List<ConsumoResponse>> getConsumosByDispositivo(@PathVariable UUID id) {
        return consumoService.getConsumosByDispositivo(id);
    }

    @GetMapping("/relatorio")
    public ResponseEntity<RelatorioResponse> getRelatorio(@RequestParam("data") LocalDate data) {
        return consumoService.getRelatorio(data);
    }
}
