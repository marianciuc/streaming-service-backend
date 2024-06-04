package zut.wi.streamingservice.rest;


import com.google.rpc.context.AttributeContext;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zut.wi.streamingservice.dto.StripeTokenDto;
import zut.wi.streamingservice.dto.request.AuthenticationRequest;
import zut.wi.streamingservice.dto.request.RegisterRequest;
import zut.wi.streamingservice.dto.response.AuthenticationResponse;
import zut.wi.streamingservice.service.AuthenticationService;

@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody
                                                               AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
