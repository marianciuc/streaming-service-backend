package zut.wi.streamingservice.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zut.wi.streamingservice.dto.request.AuthenticationRequest;
import zut.wi.streamingservice.dto.request.RegisterRequest;
import zut.wi.streamingservice.dto.response.AuthenticationResponse;
import zut.wi.streamingservice.service.UserService;

@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody
                                                               AuthenticationRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
