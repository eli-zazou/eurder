package be.cm.order.entities;

import be.cm.order.dto.OrderDto;

import java.util.List;

// todo is it really in entity? because it's not a JPA entity.
public class OrderReport {

    private List<OrderDto> orderDtos;
    private double totalPrice;

    public OrderReport(List<OrderDto> orderDtos) {
        this.orderDtos = orderDtos;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return orderDtos.stream()
                .map((OrderDto::getPrice))
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private OrderReport(){
        // for Jackson
    }

    public List<OrderDto> getOrderDtos() {
        return orderDtos;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
