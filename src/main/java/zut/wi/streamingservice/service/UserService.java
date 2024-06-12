package zut.wi.streamingservice.service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import zut.wi.streamingservice.dto.response.MessageResponse;
import zut.wi.streamingservice.model.User;
import zut.wi.streamingservice.model.UserSettings;
import zut.wi.streamingservice.repository.SettingsRepository;
import zut.wi.streamingservice.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
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

//    public MessageResponse blockUser(UUID userId){
//        try {
//
//        }
//    }
}
