package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ratings")
public class Rating extends BaseEntity{
    @ManyToOne
    private User user;

    @ManyToOne
    private Content content;

    @Column(name = "rating")
    private Integer rating;
}
