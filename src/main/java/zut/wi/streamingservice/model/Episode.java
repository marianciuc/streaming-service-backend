package zut.wi.streamingservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Table(name = "episodes")
@Data
@Getter
@Setter
@Entity
public class Episode extends BaseEntity{
    @Column(name = "number")
    private Integer number;
}

// TODO Refs
