package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "user_settings")
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserSettings extends BaseEntity{
    @Column(name = "language")
    private String language;

    @Column(name = "theme")
    private String theme;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
