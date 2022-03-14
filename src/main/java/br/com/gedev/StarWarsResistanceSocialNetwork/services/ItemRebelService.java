package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.ItemRebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemRebelService {
    private final ItemRebelRepository itemRebelRepository;

    public ItemRebel createItemRebel(ItemRebel itemRebel) {
        return itemRebelRepository.save(itemRebel);
    }

    public List<ItemRebel> findItemsRebel(Rebel rebel) {
        return itemRebelRepository.findByRebelId(rebel.getId());
    }
}
