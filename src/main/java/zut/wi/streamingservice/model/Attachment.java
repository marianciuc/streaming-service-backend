package zut.wi.streamingservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "attachment")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends BaseEntity{
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "url")
    private String url;

    @Column(name = "is_video")
    private boolean isVideo;

    @Column(name = "scale")
    private String scale;

    @Lob
    @JsonIgnore
    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;

    @Column(name = "description")
    private String description;
}
