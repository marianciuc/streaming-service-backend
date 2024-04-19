package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import zut.wi.streamingservice.enums.RecordStatus;

import java.util.Date;
import java.util.UUID;

@Data
@EnableJpaAuditing
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP()")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP()")
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "record_status", length = 30, columnDefinition = "varchar(30) default 'ACTIVE'")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private RecordStatus recordStatus = RecordStatus.ACTIVE;
}
