package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateItemDealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.DealItem;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Item;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.enums.ItemsEnum;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.DealItemPointsNotEquivalentException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.InsufficientItemsException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.InvalidItemIdException;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.ItemRebelService;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class DealItemBusiness {
    private final ItemService itemService;
    private final ItemRebelService itemRebelService;

    public List<DealItem> validateAndAggregateDealItems(
            List<CreateItemDealDTO> dtoList, Rebel sourceRebel, Rebel receiverRebel)
            throws InvalidItemIdException, InsufficientItemsException {

        Map<UUID, CreateItemDealDTO> mapItemsDeal = new HashMap<>();

        for (ItemsEnum itemEnum : ItemsEnum.values()) {
            UUID itemId = itemEnum.getUUID();

            CreateItemDealDTO itemMap = new CreateItemDealDTO();
            itemMap.setItemId(itemId);
            itemMap.setQuantity(0);

            mapItemsDeal.put(itemId, itemMap);
        }

        for (CreateItemDealDTO dto : dtoList) {
            UUID itemId = dto.getItemId();

            // validate item id
            if (!mapItemsDeal.containsKey(itemId)) {
                throw new InvalidItemIdException();
            }

            // aggregate quantities
            CreateItemDealDTO mapDto = mapItemsDeal.get(itemId);
            mapDto.setQuantity(mapDto.getQuantity() + dto.getQuantity());
        }

        List<DealItem> dealItemList = new ArrayList<>();

        for (Map.Entry<UUID, CreateItemDealDTO> entrySet : mapItemsDeal.entrySet()) {
            CreateItemDealDTO dto = entrySet.getValue();

            if (dto.getQuantity() <= 0) {
                continue;
            }

            Item item = itemService.findItemByUUID(dto.getItemId());
            ItemRebel itemRebel = itemRebelService.findOrCreateItemRebel(item, sourceRebel);

            if (itemRebel.getQuantity() < dto.getQuantity()) {
                throw new InsufficientItemsException();
            }

            DealItem dealItem = new DealItem();
            dealItem.setItem(item);
            dealItem.setQuantity(dto.getQuantity());
            dealItem.setSourceRebel(sourceRebel);
            dealItem.setReceiverRebel(receiverRebel);

            dealItemList.add(dealItem);
        }

        return dealItemList;
    }

    public void validateItemPoints(List<DealItem> dealItemsRebel1, List<DealItem> dealItemsRebel2)
            throws DealItemPointsNotEquivalentException {
        if (evaluateItemPoints(dealItemsRebel1) != evaluateItemPoints(dealItemsRebel2)) {
            throw new DealItemPointsNotEquivalentException();
        }
    }

    private double evaluateItemPoints(List<DealItem> dealItems) {
        double total = 0;

        for (DealItem dealItem : dealItems) {
            total += dealItem.getQuantity() * dealItem.getItem().getValue();
        }

        return total;
    }
}
