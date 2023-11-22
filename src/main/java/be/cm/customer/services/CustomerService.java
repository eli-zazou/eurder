package be.cm.customer.services;

import be.cm.customer.dto.CreateCustomeDto;
import be.cm.customer.entities.Customer;
import be.cm.customer.repositories.CustomerRepository;
import be.cm.security.services.UserMapper;
import be.cm.security.user.User;
import be.cm.security.user.UserRepository;
import be.cm.security.user.UserRole;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class CustomerService {
    private static final Logger logger = Logger.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private String errorMessage;

    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public Customer createCustomerAccount(CreateCustomeDto createCustomeDto) {
        if (customerRepository.findCustomerByEmailAddress(createCustomeDto.emailAddress()).isPresent()) {
            errorMessage = "This email address is already used by another user.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (createCustomeDto.password() == null || createCustomeDto.password().trim().isEmpty()) {
            errorMessage = "Password not provided.";
        }
        Customer customerToAdd = CustomerMapper.mapToEntity(createCustomeDto);
        customerRepository.addCustomer(customerToAdd);
        User user = UserMapper.mapToEntity(createCustomeDto, UserRole.CUSTOMER, customerToAdd);
        userRepository.addUser(user);
        return customerToAdd;
    }

    public Collection<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customerToReturn = customerRepository.getCustomerById(id);
        if (customerToReturn.isEmpty()) {
            errorMessage = "Customer with id: " + id + " doesn't exist.";
            logger.error(errorMessage);
            throw new NotFoundException(errorMessage);  // todo william mentioned something about WebApplicationException being thrown in Resource/Controller
        }
        return customerToReturn.get();
    }
}
