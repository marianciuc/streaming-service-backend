package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Table(name = "seasons")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Season extends BaseEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany
    private List<Episode> episodes;

    @ManyToOne
    private Content content;

    @Column(name = "number")
    private Integer number;
}
