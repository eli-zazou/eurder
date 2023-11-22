package be.cm.customer.dto;

import be.cm.customer.entities.Address;

public record CreateCustomeDto(String firstname, String lastname, String emailAddress, String password, Address address,
                               String phoneNumber) {
}
