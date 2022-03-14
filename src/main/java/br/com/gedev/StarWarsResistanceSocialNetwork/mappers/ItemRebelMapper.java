package br.com.gedev.StarWarsResistanceSocialNetwork.mappers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateItemRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.ItemRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Item;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ItemRebelMapper {
    private final ModelMapper modelMapper;
    private final ItemService itemService;

    public List<ItemRebel> fromCreateDTOListToEntityList(List<CreateItemRebelDTO> createItemRebelDTOList) {
        List<ItemRebel> itemRebelList = new ArrayList<>();

        for (CreateItemRebelDTO dto : createItemRebelDTOList) {
            ItemRebel entity = modelMapper.map(dto, ItemRebel.class);
            itemService.findItemByUUID(dto.getItemId()).ifPresent(entity::setItem);
            itemRebelList.add(entity);
        }

        return itemRebelList;
    }

    public ItemRebelDTO fromEntityToDTO(ItemRebel entity) {
        ItemRebelDTO dto = modelMapper.map(entity, ItemRebelDTO.class);

        Item item = entity.getItem();
        dto.setItemId(item.get_id());
        dto.setItemName(item.getName());

        return dto;
    }
}
