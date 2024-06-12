package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Table(name = "content")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Content extends BaseEntity{
    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "backgroud_url")
    private String backgroundUrl;

    @Column(name = "small_poster_url")
    private String small_poster_url;

    @Column(name = "watches")
    private Integer watches;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "content_genres",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AdditionalMedia> additionalMedia = new ArrayList<>();
}
