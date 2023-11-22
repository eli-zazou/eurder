package be.cm.security.user;

import be.cm.customer.entities.Customer;
import be.cm.security.Feature;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    @OneToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;

    protected User(){
        // for JPA
    }

    public User(String username, String password, UserRole userRole, Customer customer) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.customer = customer;
    }

    public boolean canHaveAccessTo(Feature feature) {
        return userRole.containsFeature(feature);
    }

    public boolean doesPasswordMatch(String passwordToCheck) {
        return this.password.equals(passwordToCheck);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Customer getCustomer() {
        return customer;
    }
}
