package be.cm.order.entities;

import be.cm.item.entities.Item;
import jakarta.persistence.*;
import org.jboss.logging.Logger;

import java.time.LocalDate;

@Entity
public class ItemGroup {
    private static final int SHIPPING_TIME_IN_DAYS_AVAILABLE_ITEM = 1;
    private static final int SHIPPING_TIME_IN_DAYS_UNAVAILABLE_ITEM = 7;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemgroup_seq")
    @SequenceGenerator(name = "itemgroup_seq", sequenceName = "itemgroup_seq", allocationSize = 1)
    private Long id;
    private int amount;
    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    @ManyToOne
    @JoinColumn(name = "fk_item_id")
    private Item item;

    @Column(name = "unit_price_at_order")
    private double unitPriceAtOrder;

    /*
    In case the client ordered the same product more than once in a single order,
    doesn't work well because the amount isn't decreased.
    * */
    public ItemGroup(int amount, Item item) {
        // todo is it better to assign shipping date in service or itemGroups?
        if (amount < 1){
            final Logger logger = Logger.getLogger(ItemGroup.class);
            String errorMessage = "You cannot create an order of " + amount + " amount.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        this.amount = amount;
        this.item = item;
        this.unitPriceAtOrder = item.getPrice();
        this.shippingDate = calculateShippingDate();
    }

    protected ItemGroup(){
        // for JPA
    }

    private LocalDate calculateShippingDate() {
        if (item.getAmount() < amount){
            return LocalDate.now().plusDays(SHIPPING_TIME_IN_DAYS_UNAVAILABLE_ITEM);
        }
        return LocalDate.now().plusDays(SHIPPING_TIME_IN_DAYS_AVAILABLE_ITEM);
    }

    public double calculateTotalPrice(){
        return amount*unitPriceAtOrder;
    }

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public Item getItem() {
        return item;
    }

    public double getUnitPriceAtOrder() {
        return unitPriceAtOrder;
    }
}
