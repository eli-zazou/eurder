package be.cm.customer.entities;

import be.cm.utils.EmailValidator;
import jakarta.persistence.*;
import org.jboss.logging.Logger;

@Entity
public class Customer {

//    @Transient useless because JPA ignore static fields
    private static final Logger logger = Logger.getLogger(Customer.class);
    @Transient
    private String errorMessage;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    private String firstname;
    private String lastname;
    private String emailAddress;
    @Embedded
    private Address address;
    @Column(name = "phone_number")
    private String phoneNumber;

    protected Customer() {
        // for JPA
    }

    public Customer(String firstname, String lastname, String emailAddress,
                    Address address, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        validateCustomerFieldNotNull();
        if (!EmailValidator.isValidEmail(this.emailAddress)) {
            errorMessage = "EmailAddress: " + this.emailAddress + " not valid.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateCustomerFieldNotNull() {
        if (firstname == null|| firstname.trim().isEmpty()) {
            errorMessage = "Firstname not Provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (lastname == null|| this.lastname.trim().isEmpty()) {
            errorMessage = "Lastname not Provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (emailAddress == null || emailAddress.trim().isEmpty()) {
            errorMessage= "Email address cannot be empty or null.";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (address == null) {
            errorMessage = "Address not Provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);

        }
        if (phoneNumber == null|| phoneNumber.trim().isEmpty()) {
            errorMessage = "Phone Number not Provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String makeFullName() {
        return getFirstname() + " " + getLastname();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
