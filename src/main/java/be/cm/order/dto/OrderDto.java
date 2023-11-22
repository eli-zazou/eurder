package be.cm.order.dto;

import java.util.List;

public class OrderDto {

    private Long id;

    private List<ItemGroupDto> itemGroups;
    private double totalPrice;


    public OrderDto(List<ItemGroupDto> itemGroups, double totalPrice) {
        this.itemGroups = itemGroups;
        this.totalPrice = totalPrice;
    }

    private OrderDto(){
        // for Jackson
    }

    public List<ItemGroupDto> getItemGroups() {
        return itemGroups;
    }

    public double getPrice() {
        return totalPrice;
    }
}
