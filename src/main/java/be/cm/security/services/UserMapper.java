package be.cm.security.services;

import be.cm.customer.dto.CreateCustomeDto;
import be.cm.customer.entities.Customer;
import be.cm.security.user.User;
import be.cm.security.user.UserRole;

public class UserMapper {
    public static User mapToEntity(CreateCustomeDto createCustomeDto, UserRole userRole, Customer customerToAdd) {
        return new User(createCustomeDto.emailAddress(), createCustomeDto.password(), UserRole.CUSTOMER, customerToAdd);
    }
}
