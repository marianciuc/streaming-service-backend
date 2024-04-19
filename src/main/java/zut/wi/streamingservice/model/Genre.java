package zut.wi.streamingservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table(name = "genres")
@Data
@Getter
@Setter
@Entity
public class Genre extends BaseEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
