package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.ItemDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Item;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.ItemMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ItemBusiness {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public List<ItemDTO> listAllItems() {
        List<ItemDTO> dtoList = new ArrayList<>();

        for (Item item : itemService.findAllItems()) {
            dtoList.add(itemMapper.fromEntityToDTO(item));
        }

        return dtoList;
    }
}
