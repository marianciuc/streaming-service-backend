package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zut.wi.streamingservice.enums.ModerationStatus;

@Table(name = "comments")
@Data
@Getter
@Setter
@Entity
public class Comment extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "approve_status", length = 30, columnDefinition = "varchar(30) default 'PENDING'")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private ModerationStatus approveStatus = ModerationStatus.PENDING;

    @Column(name = "comment_text")
    private String commentText;

    @ManyToOne
    private User user;

    @ManyToOne
    private Content content;
}
