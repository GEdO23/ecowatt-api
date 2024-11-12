package omcorp.ecowatt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.auth.*;
import omcorp.ecowatt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponse> auth(@RequestBody @Valid AuthUserDto authDto) {
        return userService.auth(authDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody @Valid SignUpUserRequest userDto) {
        return userService.signUp(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UpdateUserRequest userDto) {
        return userService.updateUser(userDto);
    }
}
