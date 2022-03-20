package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Deal;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.DealItem;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DealService {
    private final DealRepository dealRepository;
    private final DealItemService dealItemService;
    private final ItemRebelService itemRebelService;

    public Deal createDeal(Deal deal, List<DealItem> dealItemsRebel1, List<DealItem> dealItemsRebel2) {
        Deal dealCreated = dealRepository.save(deal);

        saveDealItems(dealCreated, dealItemsRebel1);
        saveDealItems(dealCreated, dealItemsRebel2);

        return dealCreated;
    }

    public List<Deal> findAllDeals() {
        return dealRepository.findAllDeals();
    }

    private void saveDealItems(Deal deal, List<DealItem> dealItemsRebel) {
        for (DealItem dealItemToCreate : dealItemsRebel) {
            dealItemToCreate.setDeal(deal);

            DealItem dealItem = dealItemService.createDealItem(dealItemToCreate);

            itemRebelService.updateItemRebelQuantity(
                    dealItem.getSourceRebel(), dealItem.getItem(), -dealItem.getQuantity());

            itemRebelService.updateItemRebelQuantity(
                    dealItem.getReceiverRebel(), dealItem.getItem(), dealItem.getQuantity());

            deal.getDealItems().add(dealItem);
        }
    }
}
