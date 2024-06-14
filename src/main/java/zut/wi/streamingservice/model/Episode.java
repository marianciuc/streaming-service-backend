package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "episodes")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Episode extends BaseEntity{
    @Column(name = "episode_number")
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    private Season season;

    @Column(name = "preview_photo")
    private String previewPhotoUrl;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "episode_id")
    private List<Attachment> attachment;
}
