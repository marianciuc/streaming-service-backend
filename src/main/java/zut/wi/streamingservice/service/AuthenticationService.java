package zut.wi.streamingservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.request.AuthenticationRequest;
import zut.wi.streamingservice.dto.request.RegisterRequest;
import zut.wi.streamingservice.dto.response.AuthenticationResponse;
import zut.wi.streamingservice.enums.RoleEnum;
import zut.wi.streamingservice.model.User;
import zut.wi.streamingservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * The register function takes in a RegisterRequest object, which contains the user's first name, last name, email address and password.
     * It then creates a new User object with the given information and saves it to the database.
     * Finally, it generates an authentication token for that user using JWT (JSON Web Token) and returns an AuthenticationResponse containing that token.

     *
     * @param RegisterRequest request Get the user's first name, last name, email and password
     *
     * @return An authenticationresponse object
     *
     * @docauthor Trelent
     */
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    /**
     * The authenticate function is responsible for authenticating a user.
     * It takes in an AuthenticationRequest object, which contains the email and password of the user.
     * The function then uses Spring Security's authentication manager to authenticate the credentials provided by the user.
     * If successful, it will generate a JWT token using our JwtService class and return it as part of an AuthenticationResponse object.

     *
     * @param AuthenticationRequest request Get the email and password from the request body
     *
     * @return An authenticationresponse object
     *
     * @docauthor Trelent
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }
}
