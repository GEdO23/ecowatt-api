package omcorp.ecowatt.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.auth.AuthUserDto;
import omcorp.ecowatt.dto.auth.SignUpUserRequest;
import omcorp.ecowatt.dto.auth.UserAuthResponse;
import omcorp.ecowatt.dto.auth.UserResponse;
import omcorp.ecowatt.entities.User;
import omcorp.ecowatt.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.repository.findByEmail(username);

        return optionalUser.map(users -> new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(), new ArrayList<GrantedAuthority>()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }

    public ResponseEntity<UserAuthResponse> auth(@Valid AuthUserDto authDto) {
        User user = this.repository.findByEmail(authDto.getEmail()).orElseThrow(() -> new RuntimeException("Invalid User Credentials."));
        if (!this.passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid User Credentials.");
        }

        UserAuthResponse response = UserAuthResponse.builder()
                .id(user.getId())
                .nome(user.getName())
                .email(user.getEmail())
                .token(jwtService.generateToken(user))
                .build();

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<UserResponse> signUp(@Valid SignUpUserRequest userDto) {
        User newUser = User.builder()
                .name(userDto.getNome())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getSenha()))
                .build();

        newUser = repository.save(newUser);

        UserResponse userResponse = UserResponse.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .nome(newUser.getName())
                .build();

        return ResponseEntity.ok(userResponse);
    }
}
