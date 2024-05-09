package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Table(name = "episodes")
@Data
@Entity
public class Episode extends BaseEntity{
    @Column(name = "number")
    private Integer number;

    @ManyToOne
    private Season season;

    @OneToOne
    private Attachment attachment;
}
