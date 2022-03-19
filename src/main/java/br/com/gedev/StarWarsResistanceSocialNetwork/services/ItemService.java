package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Item;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.InvalidItemIdException;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public Optional<Item> findOptionalItemByUUID(UUID itemId) {
        return itemRepository.findByUUID(itemId);
    }

    public Item findItemByUUID(UUID itemId) throws InvalidItemIdException {
        return findOptionalItemByUUID(itemId).orElseThrow(InvalidItemIdException::new);
    }

    public Iterable<Item> findAllItems() {
        return itemRepository.findAll();
    }
}
