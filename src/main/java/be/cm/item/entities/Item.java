package be.cm.item.entities;

import be.cm.customer.services.CustomerService;
import be.cm.item.dto.UpdateItemDto;
import jakarta.persistence.*;
import org.jboss.logging.Logger;

import java.util.StringJoiner;

@Entity
public class Item {

    private static final Logger logger = Logger.getLogger(Item.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int amount;
    @Transient
    private String errorMessage;

    public Item(String name, String description, double price, int amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        validateFieldsNotNull();
    }

    public String update(UpdateItemDto updateItemDto) {
        StringJoiner updatedFields = new StringJoiner(", ", "", " updated.");
        if (canUpdateField(updateItemDto.name(), "name", updatedFields)) {
            name = updateItemDto.name();
        }
        if (canUpdateField(updateItemDto.description(), "description", updatedFields)) {
            description = updateItemDto.description();
        }
        if (canUpdateField(updateItemDto.price(), "price", updatedFields)) {
            price = updateItemDto.price();
        }
        if (canUpdateField(updateItemDto.amount(), "amount", updatedFields)) {
            amount = updateItemDto.amount();
        }

        if (updatedFields.length() == 0) {
            errorMessage = "No fields were updated.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return updatedFields.toString();
    }

    private boolean canUpdateField(Object newValue, String fieldName, StringJoiner updatedFields) {
        if (newValue != null) {
            updatedFields.add(fieldName);
            return true;
        }
        return false;
    }

    private void validateFieldsNotNull() {
        if (this.name == null || name.trim().isEmpty()) {
            errorMessage = " Name Null or Not provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (this.description == null || description.trim().isEmpty()) {
            errorMessage = " Description Null or Not provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (price == 0.0) {
            errorMessage = " price not provided or Free item.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (amount == 0) {
            errorMessage = " amount not provided or no stock provided.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    protected Item() {
        // for JPA
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }


    public void order(int amountOrdered) {
        this.amount -= amountOrdered;
    }
}

