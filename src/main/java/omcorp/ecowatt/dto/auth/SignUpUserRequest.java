package omcorp.ecowatt.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserRequest {

    @NotBlank(message = "Por favor insira seu nome.")
    @Size(min = 3, max = 255)
    private String nome;

    @Email
    @NotNull(message = "Por favor insira o email.")
    private String email;

    @Size(min = 3, max = 100)
    @NotNull(message = "Por favor insira a senha.")
    private String senha;
}
