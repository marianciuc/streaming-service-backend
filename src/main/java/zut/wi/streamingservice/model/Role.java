package zut.wi.streamingservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "description")
    private String description;
}
