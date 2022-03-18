package br.com.gedev.StarWarsResistanceSocialNetwork.mappers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateItemRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.ItemRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Item;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemRebelMapper {
    private final ModelMapper modelMapper;
    private final ItemService itemService;

    public ItemRebel fromCreateDTOToEntity(CreateItemRebelDTO createItemRebelDTO) {
        ItemRebel entity = modelMapper.map(createItemRebelDTO, ItemRebel.class);
        itemService.findOptionalItemByUUID(createItemRebelDTO.getItemId()).ifPresent(entity::setItem);

        return entity;
    }

    public ItemRebelDTO fromEntityToDTO(ItemRebel entity) {
        ItemRebelDTO dto = modelMapper.map(entity, ItemRebelDTO.class);

        Item item = entity.getItem();
        dto.setItemId(item.get_id());
        dto.setItemName(item.getName());

        return dto;
    }
}
