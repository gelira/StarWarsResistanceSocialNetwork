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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<RebelDTO> listAllRebels(Pageable pageable) {
        return rebelService.listAllRebels(pageable).map(rebelMapper::fromEntityToRebelDTO);
    }
}
