package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.type.SqlTypes;
import zut.wi.streamingservice.enums.AdditionalMediaType;

@Data
@Table(name = "additional_media")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalMedia extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 30)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private AdditionalMediaType type;

    @Column(name = "url")
    private String url;

    @OneToOne
    private Attachment attachment;
}