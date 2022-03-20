package br.com.gedev.StarWarsResistanceSocialNetwork.mappers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.DealItemDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.DealItem;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Item;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DealItemMapper {
    private final ModelMapper modelMapper;
    private final RebelMapper rebelMapper;

    public DealItemDTO fromEntityToDTO(DealItem dealItem) {
        DealItemDTO dto = modelMapper.map(dealItem, DealItemDTO.class);

        Item item = dealItem.getItem();

        dto.setItemId(item.get_id());
        dto.setItemName(item.getName());
        dto.setSourceRebel(rebelMapper.fromEntityToRebelNameDTO(dealItem.getSourceRebel()));
        dto.setReceiverRebel(rebelMapper.fromEntityToRebelNameDTO(dealItem.getReceiverRebel()));

        return dto;
    }
}
