package omcorp.ecowatt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispositivoRequest {

    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(max = 255, min = 3, message = "O nome tem tamanho máximo de 255 caracteres e mínimo de 3.")
    private String nome;

    @NotBlank(message = "O local não pode estar vazio.")
    @Size(max = 255, min = 3, message = "O local tem tamanho máximo de 255 caracteres e mínimo de 3.")
    private String local;

    @NotBlank(message = "O tipo não pode estar vazio.")
    @Size(max = 255, min = 3, message = "O tipo tem tamanho máximo de 255 caracteres e mínimo de 3.")
    private String tipo;

    @NotNull(message = "O Limite de Consumo não pode ser nulo.")
    private BigDecimal limiteConsumo;
}
