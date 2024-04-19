package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Table(name = "content")
@Data
@Getter
@Setter
@Entity
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
}
