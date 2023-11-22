package be.cm.order.dto;

import java.util.List;

public record CreateOrderDto(List<CreateItemGroupDto> itemsToOrder) {
}
