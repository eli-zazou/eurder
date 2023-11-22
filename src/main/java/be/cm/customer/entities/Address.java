package be.cm.customer.entities;

import jakarta.persistence.Embeddable;
import org.jboss.logging.Logger;

@Embeddable
public class Address {
    String streetName;
    String streetNumber;
    String locality;
    short postalCode;

    protected Address() {
        // for JPA and Jackson
    }

    public Address(String streetName, String streetNumber, short postalCode, String locality) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.locality = locality;
        this.postalCode = postalCode;
        validateFieldsNotNull();
    }

    private void validateFieldsNotNull() {
        Logger logger = Logger.getLogger(Address.class);
        String errorMessage;
        if (streetName == null || streetName.trim().isEmpty()) {
            errorMessage = "Street Name Not provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (streetNumber == null || streetNumber.trim().isEmpty()) {
            errorMessage = "Street Number Not provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (locality == null || locality.trim().isEmpty()) {
            errorMessage = "Locality Not provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);

        }
        if (postalCode == 0) {
            errorMessage = "Postal code Not provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }


    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getLocality() {
        return locality;
    }

    public short getPostalCode() {
        return postalCode;
    }
}
