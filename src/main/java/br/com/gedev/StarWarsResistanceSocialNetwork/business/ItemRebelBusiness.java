package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateItemRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.enums.ItemsEnum;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.InvalidItemIdException;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.ItemRebelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class ItemRebelBusiness {
    private final ItemRebelMapper itemRebelMapper;

    public List<ItemRebel> validateAndAggregateItems(
            List<CreateItemRebelDTO> createItemRebelDTOs) throws InvalidItemIdException {

        ItemsEnum[] itemsEnums = ItemsEnum.values();
        Map<UUID, CreateItemRebelDTO> mapItems = new HashMap<>();

        for (ItemsEnum itemEnum : itemsEnums) {
            UUID itemId = itemEnum.getUUID();

            CreateItemRebelDTO itemMap = new CreateItemRebelDTO();
            itemMap.setItemId(itemId);
            itemMap.setQuantity(0);

            mapItems.put(itemId, itemMap);
        }

        for (CreateItemRebelDTO createItemRebelDTO : createItemRebelDTOs) {
            UUID itemId = createItemRebelDTO.getItemId();

            // Validate item id
            if (!mapItems.containsKey(itemId)) {
                throw new InvalidItemIdException();
            }

            // Aggregate quantities
            CreateItemRebelDTO itemMap = mapItems.get(itemId);
            itemMap.setQuantity(itemMap.getQuantity() + createItemRebelDTO.getQuantity());
        }

        List<ItemRebel> entitiesList = new ArrayList<>();
        for (Map.Entry<UUID, CreateItemRebelDTO> mapItem : mapItems.entrySet()) {
            entitiesList.add(itemRebelMapper.fromCreateDTOToEntity(mapItem.getValue()));
        }

        return entitiesList;
    }
}
