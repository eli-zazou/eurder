package be.cm.item.services;

import be.cm.item.dto.CreateItemDto;
import be.cm.item.dto.ItemDto;
import be.cm.item.dto.UpdateItemDto;
import be.cm.item.entities.Item;
import be.cm.item.repositories.ItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

import java.util.Optional;

@ApplicationScoped
@Transactional
public class ItemService {
    private static final Logger logger = Logger.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Item addItem(CreateItemDto createItemDto) {
        Item itemToAdd = ItemMapper.mapToEntity(createItemDto);
        itemRepository.addItem(itemToAdd);
        return itemToAdd;
    }

    public String updateItem(Long id, UpdateItemDto updateItemDto) {
        Optional<Item> itemToUpdate = itemRepository.findItemById(id);
        if (itemToUpdate.isEmpty()){
            String errorMessage = "Item with id " + id + " not found.";
            logger.error(errorMessage);
            throw new NotFoundException(errorMessage);
        }
        return itemToUpdate.get().update(updateItemDto);
    }
}
