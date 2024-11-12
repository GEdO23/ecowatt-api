package omcorp.ecowatt.dto.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadosAutenticacao {

    @Email(message = "O login deve ser um email válido.")
    @NotBlank(message = "O login não pode ser nulo.")
    private String login;

    @NotBlank(message = "A senha não pode ser nula.")
    @Size(min = 5, max = 30, message = "A senha deve ter pelo menos 5 carácteres e no máximo 30")
    private String senha;
}
