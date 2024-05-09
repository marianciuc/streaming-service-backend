package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @OneToMany
    private List<Content> content;
}

// TODO Refs
