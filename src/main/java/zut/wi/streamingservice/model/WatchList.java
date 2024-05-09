package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import zut.wi.streamingservice.enums.WatchStatus;

@Data
@Entity
@Table(name = "watch_list")
public class WatchList extends BaseEntity {
    @Column(name = "watch_status")
    @Enumerated
    WatchStatus watchStatus;

    @ManyToOne
    private User user;

    @ManyToOne
    private Content content;
}
