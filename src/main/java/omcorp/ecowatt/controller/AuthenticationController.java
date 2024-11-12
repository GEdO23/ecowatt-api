package omcorp.ecowatt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.authentication.DadosAutenticacao;
import omcorp.ecowatt.dto.authentication.DadosTokenJWT;
import omcorp.ecowatt.dto.authentication.UsuarioResponse;
import omcorp.ecowatt.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        return authenticationService.efetuarLogin(dados);
    }

    @PostMapping("/signup")
    public ResponseEntity<UsuarioResponse> efetuarCadastro(@RequestBody @Valid DadosAutenticacao dados) {
        return authenticationService.efetuarCadastro(dados);
    }
}
