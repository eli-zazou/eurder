package be.cm.order.services;

import be.cm.customer.entities.Customer;
import be.cm.customer.services.CustomerService;
import be.cm.item.entities.Item;
import be.cm.item.repositories.ItemRepository;
import be.cm.order.dto.CreateItemGroupDto;
import be.cm.order.dto.CreateOrderDto;
import be.cm.order.entities.ItemGroup;
import be.cm.order.entities.Order;
import be.cm.order.entities.OrderReport;
import be.cm.order.repositories.OrderRepository;
import be.cm.security.user.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class OrderService {

    private static final Logger logger = Logger.getLogger(OrderService.class);
    private String errorMessage;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    public OrderService(ItemRepository itemRepository, OrderRepository orderRepository, CustomerService customerService) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    private Item findItemByName(String name) {
        Optional<Item> item = itemRepository.findItemByName(name);
        if (item.isEmpty()) {
            errorMessage = "Item with name " + name + " not Found.";
            logger.error(errorMessage);
            throw new NotFoundException(errorMessage);
        }
        return item.get();
    }

    public Order createOrder(CreateOrderDto createOrderDto, Customer customer) {
        List<ItemGroup> itemGroups = createOrderDto.itemsToOrder().stream()
                .map(this::createItemGroup)
                .toList();
        // todo why despite the cascade.all it didnt cascade persist? maybe i didn't understand well how the cascade works.
        Customer customerPersistState = customerService.getCustomerById(customer.getId());
        Order orderToAdd = new Order(itemGroups, customerPersistState);
        orderRepository.addOrder(orderToAdd);
        return orderToAdd;
    }

    private ItemGroup createItemGroup(CreateItemGroupDto createItemGroupDto) {
        Item itemToOrder = findItemByName(createItemGroupDto.itemName());
        ItemGroup itemGroup = new ItemGroup(createItemGroupDto.amount(), itemToOrder);
        itemToOrder.order(createItemGroupDto.amount());
        return itemGroup;
    }

    public OrderReport viewReportOfOrders(Customer customer) {
        List<Order> orders = orderRepository.getAllOrdersForCustomer(customer.getId());
        return new OrderReport(OrderMapper.mapToDto(orders));
    }

    public Order reOrder(Long id, Customer customer) {
        // todo actual price should be used not from the order + customer can reorder
        Optional<Order> order = orderRepository.getOrderByIdForCustomer(id, customer.getId());
        if (order.isEmpty()){
            errorMessage = "The customer " + customer.getEmailAddress() + " doesn't have Order of " + id + " id.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        Customer customerPersistentState = customerService.getCustomerById(customer.getId());
        throw new RuntimeException("Not Implemented.");
    }
}
