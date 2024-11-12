package omcorp.ecowatt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omcorp.ecowatt.dto.auth.AuthUserDto;
import omcorp.ecowatt.dto.auth.SignUpUserRequest;
import omcorp.ecowatt.dto.auth.UserAuthResponse;
import omcorp.ecowatt.dto.auth.UserResponse;
import omcorp.ecowatt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponse> auth(@RequestBody @Valid AuthUserDto authDto) {
        return userService.auth(authDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> singUp(@RequestBody @Valid SignUpUserRequest userDto) {
        return userService.signUp(userDto);
    }
}
