package omcorp.ecowatt.controller;

import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.alerta.AlertaResponse;
import omcorp.ecowatt.service.AlertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    @GetMapping("/dispositivo/{id}")
    public ResponseEntity<List<AlertaResponse>> getAlertasByDispositivos(@PathVariable UUID id) {
        return alertaService.getAlertasByDispositivos(id);
    }

}
