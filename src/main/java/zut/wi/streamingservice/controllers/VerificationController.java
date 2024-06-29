package zut.wi.streamingservice.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import zut.wi.streamingservice.dto.response.MessageResponse;
import zut.wi.streamingservice.service.UserService;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class VerificationController {

    private final UserService userService;

    @GetMapping("/verification/{code}")
    public ResponseEntity<MessageResponse> verify(@PathVariable String code) {
        MessageResponse messageResponse = MessageResponse.builder().message("Your email address has been confirmed.").build();
        userService.verifyEmail(code);
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<MessageResponse> sendVerificationLink() {
        MessageResponse messageResponse = MessageResponse.builder().message("Message successful sent.").build();
        userService.sendConfirmationLink();
        return ResponseEntity.ok(messageResponse);
    }
}
