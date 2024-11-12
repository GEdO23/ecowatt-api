package omcorp.ecowatt.dto.consumo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioResponse {

    private BigDecimal totalConsumo;

    private String mensagem;

    private LocalDate data;
}
