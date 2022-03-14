package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.business.ItemRebelBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateItemRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.RebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.InvalidItemIdException;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.ItemRebelMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.LocationMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.RebelMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rebels")
public class RebelController {
    private final RebelService rebelService;
    private final RebelMapper rebelMapper;
    private final LocationMapper locationMapper;
    private final ItemRebelMapper itemRebelMapper;
    private final ItemRebelBusiness itemRebelBusiness;

    @PostMapping
    public RebelDTO createRebel(@Valid @RequestBody CreateRebelDTO createRebelDTO) throws InvalidItemIdException {
        List<CreateItemRebelDTO> createItemRebelDTOList =
                itemRebelBusiness.validateAndAggregateItems(createRebelDTO.getInventory());

        Rebel rebelToCreate = rebelMapper.fromCreateDTOToEntity(createRebelDTO);
        Location locationToCreate = locationMapper.fromCreateDTOToEntity(createRebelDTO.getLocation());
        List<ItemRebel> itemRebelList = itemRebelMapper.fromCreateDTOListToEntityList(createItemRebelDTOList);

        Rebel rebelCreated = rebelService.createRebel(rebelToCreate, locationToCreate, itemRebelList);
        return rebelMapper.fromEntityToRebelDTO(rebelCreated);
    }
}
