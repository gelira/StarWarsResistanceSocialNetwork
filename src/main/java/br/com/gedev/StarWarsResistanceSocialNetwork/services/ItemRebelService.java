package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Item;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.ItemRebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemRebelService {
    private final ItemRebelRepository itemRebelRepository;

    public ItemRebel createItemRebel(ItemRebel itemRebel) {
        return itemRebelRepository.save(itemRebel);
    }

    public ItemRebel findOrCreateItemRebel(Item item, Rebel rebel) {
        Optional<ItemRebel> optItemRebel = itemRebelRepository.findByRebelAndItem(rebel.getId(), item.getId());

        if (optItemRebel.isPresent()) {
            return optItemRebel.get();
        }

        ItemRebel itemRebel = new ItemRebel();
        itemRebel.setRebel(rebel);
        itemRebel.setItem(item);
        itemRebel.setQuantity(0);

        return createItemRebel(itemRebel);
    }

    public void updateItemRebelQuantity(Rebel rebel, Item item, int change) {
        itemRebelRepository.updateQuantityItemRebel(rebel.getId(), item.getId(), change);
    }
}
