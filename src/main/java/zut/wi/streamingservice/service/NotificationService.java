package zut.wi.streamingservice.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;

    public void sendEmailConfirmation(String to, String username, String link) throws MessagingException {
        String subject = "Confirming your e-mail address";
        String templateName = "confirm-email";
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", username);
        variables.put("confirmationLink", link);

        emailService.sendHtmlEmail(to, subject, templateName, variables);
    }

    public void sendPaymentSuccess(String to, String username, String date, String recharged, String curr) throws MessagingException {
        String subject = "Successful payment";
        String templateName = "payment-success";
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", username);
        variables.put("recharged", recharged);
        variables.put("curr", curr);
        variables.put("date", date);

        emailService.sendHtmlEmail(to, subject, templateName, variables);
    }
}
