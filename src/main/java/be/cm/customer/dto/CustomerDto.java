package be.cm.customer.dto;

import be.cm.customer.entities.Address;

public record CustomerDto (Long id, String fullName, String emailAddress, Address address, String phoneNumber) {
}
