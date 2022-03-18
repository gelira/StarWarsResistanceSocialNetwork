package br.com.gedev.StarWarsResistanceSocialNetwork.mappers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.ItemDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Item;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemMapper {
    private final ModelMapper modelMapper;

    public ItemDTO fromEntityToDTO(Item entity) {
        return modelMapper.map(entity, ItemDTO.class);
    }
}
