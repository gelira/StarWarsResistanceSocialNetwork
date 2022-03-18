package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.DealItem;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.DealItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DealItemService {
    private final DealItemRepository dealItemRepository;

    public DealItem createDealItem(DealItem dealItem) {
        return dealItemRepository.save(dealItem);
    }
}
