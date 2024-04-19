package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
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
}

/*
TODO
 - Ref: content.id <  additional_media.content_id
 - Ref: additional_media.media.id - attachment.id
*/