package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.RebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.InvalidItemIdException;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.LocationMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.RebelMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class RebelBusiness {
    private final RebelService rebelService;
    private final RebelMapper rebelMapper;
    private final LocationMapper locationMapper;
    private final ItemRebelBusiness itemRebelBusiness;

    @Transactional
    public RebelDTO createRebel(CreateRebelDTO createRebelDTO) throws InvalidItemIdException {
        List<ItemRebel> itemRebelList = itemRebelBusiness.validateAndAggregateItems(createRebelDTO.getInventory());

        Rebel rebelToCreate = rebelMapper.fromCreateDTOToEntity(createRebelDTO);
        Location locationToCreate = locationMapper.fromCreateDTOToEntity(createRebelDTO.getLocation());

        Rebel rebelCreated = rebelService.createRebel(rebelToCreate, locationToCreate, itemRebelList);
        return rebelMapper.fromEntityToRebelDTO(rebelCreated);
    }

    public List<RebelDTO> listAllRebels() {
        List<RebelDTO> dtoList = new ArrayList<>();

        for (Rebel rebel : rebelService.listAllRebels()) {
            dtoList.add(rebelMapper.fromEntityToRebelDTO(rebel));
        }

        return dtoList;
    }
}
