package be.cm.order.entities;

import be.cm.customer.entities.Customer;
import be.cm.item.entities.Item;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "eurder")  // 'order' is a reserved keyword in postgres
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eurder_seq")
    @SequenceGenerator(name = "eurder_seq", sequenceName = "eurder_seq", allocationSize = 1)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fk_eurder_id")
    // todo is it better to have a join column here or join table (choice of JPA by default join table)
    private List<ItemGroup> itemGroups;
    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;

    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "order_date")
    private LocalDate orderDate;

    public Order(List<ItemGroup> itemGroups, Customer customer) {
        // todo is it better to create the itemGroups in the service or in order?
        this.itemGroups = itemGroups;
        this.customer = customer;
        this.orderDate = LocalDate.now();
        this.totalPrice = calculateTotalPrice();
    }


    private double calculateTotalPrice() {
        return itemGroups.stream()
                .map(ItemGroup::calculateTotalPrice)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    protected Order() {
        // for JPA
    }

    public Long getId() {
        return id;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
