package zut.wi.streamingservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Table(name = "categories")
@Data
@Getter
@Setter
@Entity
public class Category extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}

// TODO Refs
