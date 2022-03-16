package br.com.gedev.StarWarsResistanceSocialNetwork.mappers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.ItemRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.RebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.RebelNameDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.ItemRebelService;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class RebelMapper {
    private final ModelMapper modelMapper;
    private final LocationMapper locationMapper;
    private final ItemRebelMapper itemRebelMapper;
    private final LocationService locationService;
    private final ItemRebelService itemRebelService;

    public Rebel fromCreateDTOToEntity(CreateRebelDTO dto) {
        return modelMapper.map(dto, Rebel.class);
    }

    public RebelDTO fromEntityToRebelDTO(Rebel entity) {
        RebelDTO dto = modelMapper.map(entity, RebelDTO.class);
        locationService.findCurrentLocation(entity).ifPresent(
                location -> dto.setLocation(locationMapper.fromEntityToDTO(location)));

        List<ItemRebelDTO> itemRebelDTOList = new ArrayList<>();
        for (ItemRebel itemRebel : itemRebelService.findItemsRebel(entity)) {
            itemRebelDTOList.add(itemRebelMapper.fromEntityToDTO(itemRebel));
        }

        dto.setInventory(itemRebelDTOList);

        return dto;
    }

    public RebelNameDTO fromEntityToRebelNameDTO(Rebel entity) {
        return modelMapper.map(entity, RebelNameDTO.class);
    }
}
