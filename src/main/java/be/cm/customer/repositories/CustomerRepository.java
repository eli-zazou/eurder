package be.cm.customer.repositories;

import be.cm.customer.entities.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public void addCustomer(Customer customer) {
        persist(customer);  // in panache, it wil use the entity manager we just don't see it here does it for us
    }

    public List<Customer> getAllCustomers() {
        return listAll();
    }


    public Optional<Customer> getCustomerById(Long id) {
        return findByIdOptional(id);
    }

    public Optional<Customer> findCustomerByEmailAddress(String emailAddress) {
        return find("emailAddress", emailAddress).firstResultOptional();
    }

}
