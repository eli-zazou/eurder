package be.cm.order.services;

import be.cm.order.dto.ItemGroupDto;
import be.cm.order.entities.ItemGroup;

import java.util.List;

public class ItemGroupMapper {

    public static ItemGroupDto mapToDto(ItemGroup itemGroup){
        return new ItemGroupDto(itemGroup.getItem().getName(),
                itemGroup.getAmount(),
                itemGroup.getShippingDate(),
                itemGroup.calculateTotalPrice());
    }

    public static List<ItemGroupDto> mapToDto(List<ItemGroup> itemGroups) {
        return itemGroups.stream()
                .map((ItemGroupMapper::mapToDto))
                .toList();
    }
}
