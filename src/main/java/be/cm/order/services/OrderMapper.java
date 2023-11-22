package be.cm.order.services;

import be.cm.item.services.ItemMapper;
import be.cm.order.dto.OrderDto;
import be.cm.order.entities.Order;

import java.util.List;

public class OrderMapper {

    public static OrderDto mapToDto(Order order){
        // todo i need ItemGroupMapper. Will it work if i don't have a panache repo explicitly ????
        return new OrderDto(ItemGroupMapper.mapToDto(order.getItemGroups()), order.getTotalPrice());
    }


    public static List<OrderDto> mapToDto(List<Order> orders) {
        return orders.stream()
                .map(OrderMapper::mapToDto)
                .toList();
    }
}
