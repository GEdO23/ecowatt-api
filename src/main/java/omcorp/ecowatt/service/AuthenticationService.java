package omcorp.ecowatt.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.config.security.SecurityConfigurations;
import omcorp.ecowatt.dto.authentication.DadosAutenticacao;
import omcorp.ecowatt.dto.authentication.DadosTokenJWT;
import omcorp.ecowatt.dto.authentication.UsuarioResponse;
import omcorp.ecowatt.entities.Usuario;
import omcorp.ecowatt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    @Lazy
    @Autowired
    private AuthenticationManager manager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }

    public ResponseEntity<DadosTokenJWT> efetuarLogin(DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(DadosTokenJWT.builder().username(dados.getLogin()).token(tokenJWT).build());
    }

    public ResponseEntity<UsuarioResponse> efetuarCadastro(@Valid DadosAutenticacao dados) {
        Usuario usuario = Usuario.builder()
                .login(dados.getLogin())
                .senha(passwordEncoder.encode(dados.getLogin()))
                .build();

        usuario = usuarioRepository.save(usuario);

        var usuarioResponse = UsuarioResponse.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .build();

        var uriBuilder = UriComponentsBuilder.newInstance();
        var uri = uriBuilder.path("/auth/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(usuarioResponse);
    }
}
