package zut.wi.streamingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "directors")
@Data
@Getter
@Setter
@Entity
public class Director extends BaseEntity{
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "desctiprion")
    private String description;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "birthday_date")
    @Temporal(TemporalType.DATE)
    private Date birthdayDate;
}
