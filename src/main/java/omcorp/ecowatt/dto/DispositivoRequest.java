package omcorp.ecowatt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispositivoRequest {

    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(max = 255, min = 3, message = "O nome tem tamanho máximo de 255 caracteres e mínimo de 3.")
    private String nome;
}
