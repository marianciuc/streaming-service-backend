package zut.wi.streamingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity{
    @JsonIgnore
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "birthday_date")
    @Temporal(TemporalType.DATE)
    private Date birthdayDate;

    @Column(name = "city")
    private String city;

    @OneToOne
    private UserSettings userSettings;

    @ManyToOne
    private UserSubscription activeSubscription;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();
}
