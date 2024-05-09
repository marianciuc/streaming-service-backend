package zut.wi.streamingservice.dto.request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String id;
    private String username;
    private String accessToken;
    private String refreshToken;
}
