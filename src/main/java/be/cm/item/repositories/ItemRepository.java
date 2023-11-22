package be.cm.item.repositories;

import be.cm.item.entities.Item;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {

    public void addItem(Item itemToAdd) {
        persist(itemToAdd);
    }

    public Optional<Item> findItemById(Long id) {
        return findByIdOptional(id);
    }

    public Optional<Item> findItemByName(String itemName) {
        return Optional.ofNullable(find("lower(name) = lower(?1)", itemName).firstResult());
    }
}
