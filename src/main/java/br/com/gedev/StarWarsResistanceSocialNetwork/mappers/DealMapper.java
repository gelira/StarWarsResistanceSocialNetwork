package br.com.gedev.StarWarsResistanceSocialNetwork.mappers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.DealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Deal;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.DealItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class DealMapper {
    private final ModelMapper modelMapper;
    private final RebelMapper rebelMapper;
    private final DealItemMapper dealItemMapper;

    public DealDTO fromEntityToDTO(Deal entity) {
        DealDTO dto = modelMapper.map(entity, DealDTO.class);

        dto.setRebel1(rebelMapper.fromEntityToRebelNameDTO(entity.getRebel1()));
        dto.setRebel2(rebelMapper.fromEntityToRebelNameDTO(entity.getRebel2()));
        dto.setItems(new ArrayList<>());

        for (DealItem dealItem : entity.getDealItems()) {
            dto.getItems().add(dealItemMapper.fromEntityToDTO(dealItem));
        }

        return dto;
    }
}
