package be.cm.item.services;

import be.cm.item.dto.CreateItemDto;
import be.cm.item.dto.ItemDto;
import be.cm.item.entities.Item;

public class ItemMapper {

    public static ItemDto mapToDto(Item item){
        return new ItemDto(item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getAmount());
    }

    public static Item mapToEntity(CreateItemDto createItemDto){
        return new Item(createItemDto.getName(),
                createItemDto.getDescription(),
                createItemDto.getPrice(),
                createItemDto.getAmount());
    }

}
