package be.cm.order.repositories;

import be.cm.order.entities.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {
    public void addOrder(Order order) {
        persist(order);
    }

    public List<Order> getAllOrdersForCustomer(Long id) {
        return find("customer.id", id).list();
    }

    public Optional<Order> getOrderByIdForCustomer(Long orderId, Long customerId) {
        return find("id = ?1 and customer.id = ?2", orderId, customerId).firstResultOptional();
    }
}
