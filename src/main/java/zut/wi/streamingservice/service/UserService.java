package zut.wi.streamingservice.service;


import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zut.wi.streamingservice.dto.request.AuthenticationRequest;
import zut.wi.streamingservice.dto.request.RegisterRequest;
import zut.wi.streamingservice.dto.response.AuthenticationResponse;
import zut.wi.streamingservice.enums.RoleEnum;
import zut.wi.streamingservice.exceptions.EmailHasAlreadyBeenConfirmed;
import zut.wi.streamingservice.exceptions.NotFoundException;
import zut.wi.streamingservice.exceptions.SubscriptionException;
import zut.wi.streamingservice.model.User;
import zut.wi.streamingservice.repository.SettingsRepository;
import zut.wi.streamingservice.repository.UserRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final NotificationService notificationService;

    /**
     * The register function takes in a RegisterRequest object, which contains the user's first name, last name, email address and password.
     * It then creates a new User object with the given information and saves it to the database.
     * Finally, it generates an authentication token for that user using JWT (JSON Web Token) and returns an AuthenticationResponse containing that token.
     *
     * @param RegisterRequest request Get the user's first name, last name, email and password
     * @return An authenticationresponse object
     *
     * @docauthor Trelent
     */
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.USER)
                .country(request.getCountry())
                .username(request.getUsername())
                .build();
        userRepository.save(user);
        sendConfirmationLink(user);
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

    public User getUserContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return userRepository.getByEmail(username);
        } else {
            throw new RuntimeException("User authentication error.");
        }
    }

    public void enableSubscription(User user) {
        try {
            user.setRole(RoleEnum.SUBSCRIBED_USER);
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error assigning a subscription to a user: {}", user.getId(), e);
            throw new SubscriptionException("Error assigning a subscription to a user. Contact your system administrator.", e);
        }
    }

    public void disableSubscription(User user) {
        try {
            user.setRole(RoleEnum.USER);
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error removing a subscription from a user: {}", user.getId(), e);
            throw new SubscriptionException("Error removing a subscription from a user. Contact your system administrator.", e);
        }
    }

    public void verifyEmail(String code){
        Optional<User> user = userRepository.findByConfirmationCode(code);
        if (user.isPresent()) {
            User u = user.get();
            if (u.isEmailVerified()) throw new EmailHasAlreadyBeenConfirmed("The user's mail has already been confirmed");
            u.setEmailVerified(true);
            userRepository.save(u);
        } else {
            throw new NotFoundException("The confirmation code is not valid.");
        }
    }

    public String getEmailConfirmationLink(User user) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/email/verification/")
                .path(user.getConfirmationCode())
                .toUriString();
    }

    public void sendConfirmationLink(){
        User user = getUserContext();
        sendConfirmationLink(user);
    }

    public void sendConfirmationLink(User user){
        try {
            notificationService.sendEmailConfirmation(user.getEmail(), user.getFirstnameLastname(), getEmailConfirmationLink(user));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
