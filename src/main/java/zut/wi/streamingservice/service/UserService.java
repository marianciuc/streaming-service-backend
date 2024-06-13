package zut.wi.streamingservice.service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.enums.RoleEnum;
import zut.wi.streamingservice.exceptions.SubscriptionException;
import zut.wi.streamingservice.model.User;
import zut.wi.streamingservice.repository.SettingsRepository;
import zut.wi.streamingservice.repository.UserRepository;


@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private SettingsRepository settingsRepository;

    @Autowired
    public UserService(SettingsRepository settingsRepository, UserRepository userRepository) {
        this.settingsRepository = settingsRepository;
        this.userRepository = userRepository;
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

}
