package omcorp.ecowatt.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.auth.*;
import omcorp.ecowatt.entities.User;
import omcorp.ecowatt.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Transactional
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

    @Transactional
    public ResponseEntity deleteUser(UUID id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e);
        }
    }

    @Transactional
    public ResponseEntity<UserResponse> updateUser(@Valid UpdateUserRequest userDto) {
        Optional<User> oldUser = repository.findById(userDto.getId());
        if (oldUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = oldUser.get().toBuilder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .name(userDto.getNome())
                .password(passwordEncoder.encode(userDto.getSenha()))
                .build();

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .nome(user.getName())
                .email(user.getEmail())
                .build();

        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<UserResponse> getUserById(UUID id) {
        Optional<User> op = repository.findById(id);
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = op.get();

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .nome(user.getName())
                .email(user.getEmail())
                .build();

        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<List<UserResponse>> getUsers() {
        var users = repository.findAll().stream().map(User::toUserResponse).toList();
        return ResponseEntity.ok(users);
    }
}
