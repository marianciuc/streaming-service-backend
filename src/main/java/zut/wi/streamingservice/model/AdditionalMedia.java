package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.type.SqlTypes;
import zut.wi.streamingservice.enums.AdditionalMediaType;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "url", unique = true, nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;
}