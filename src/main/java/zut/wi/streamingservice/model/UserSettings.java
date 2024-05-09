package zut.wi.streamingservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "user_settings")
@Entity
public class UserSettings extends BaseEntity{
    @Column(name = "language")
    private String language;

    @Column(name = "theme")
    private String theme;
}
