package be.cm.customer.services;

import be.cm.customer.dto.CreateCustomeDto;
import be.cm.customer.dto.CustomerDto;
import be.cm.customer.entities.Address;
import be.cm.customer.entities.Customer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static Customer mapToEntity(CreateCustomeDto createCustomeDto) {
        return new Customer(createCustomeDto.firstname(),
                createCustomeDto.lastname(),
                createCustomeDto.emailAddress(),
                new Address(createCustomeDto.address().getStreetName(),
                        createCustomeDto.address().getStreetNumber(),
                        createCustomeDto.address().getPostalCode(),
                        createCustomeDto.address().getLocality()),
                createCustomeDto.phoneNumber());
    }

    public static CustomerDto mapToDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.makeFullName(), customer.getEmailAddress(),
                customer.getAddress(), customer.getPhoneNumber());
    }

    public static List<CustomerDto> mapToDto(Collection<Customer> customers) {
        return customers.stream()
                .map((CustomerMapper::mapToDto))
                .toList();
    }
}
