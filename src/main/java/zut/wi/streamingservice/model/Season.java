package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "seasons")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Season extends BaseEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "relese_date")
    @Temporal(TemporalType.DATE)
    private Date releseDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "season")
    @OrderBy("number ASC")
    private List<Episode> episodes;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(name = "number")
    private Integer number;
}
