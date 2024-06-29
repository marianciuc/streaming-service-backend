package zut.wi.streamingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zut.wi.streamingservice.enums.RoleEnum;
import zut.wi.streamingservice.enums.SubscriptionStatus;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {
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

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "confirmation_code")
    private String confirmationCode;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSubscription> subscriptions = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private UserSettings userSettings;

    @PrePersist
    public void prePersist() {
        if (this.userSettings == null) {
            this.userSettings = new UserSettings("en", "light",this);
        }
        if (this.confirmationCode == null) this.confirmationCode = UUID.randomUUID().toString();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Subscription getActiveSubscription(){
        for (UserSubscription userSubscription : subscriptions) {
            if (userSubscription.getSubscriptionStatus() == SubscriptionStatus.ACTIVE && userSubscription.getExpiredAt().after(new Date())) {
                return userSubscription.getSubscription();
            }
        }
        return null;
    }

    public BigDecimal calculateBalance() {
        BigDecimal balance = BigDecimal.ZERO;

        for (Payment payment : payments) {
            if (payment.getTransactionType() == Payment.TransactionType.DEPOSIT) {
                balance = balance.add(payment.getAmount());
            } else if (payment.getTransactionType() == Payment.TransactionType.WITHDRAWAL) {
                balance = balance.subtract(payment.getAmount());
            }
        }

        return balance;
    }

    public String getFirstnameLastname(){return firstName + " " + lastName;}
}
