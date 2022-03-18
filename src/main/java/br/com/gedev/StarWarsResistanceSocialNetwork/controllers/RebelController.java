package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.business.RebelBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.*;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.InvalidItemIdException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.RebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.LocationMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.LocationService;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rebels")
public class RebelController {
    private final RebelService rebelService;
    private final LocationService locationService;
    private final LocationMapper locationMapper;
    private final RebelBusiness rebelBusiness;

    @PostMapping
    public RebelDTO createRebel(@Valid @RequestBody CreateRebelDTO createRebelDTO) throws InvalidItemIdException {
        return rebelBusiness.createRebel(createRebelDTO);
    }

    @PostMapping("{id}/locations")
    public LocationDTO createLocation(
            @PathVariable("id") UUID rebelId,
            @Valid @RequestBody CreateLocationDTO createLocationDTO) throws RebelNotFoundException {

        Rebel rebel = rebelService.findRebelByUUID(rebelId);
        Location locationToCreate = locationMapper.fromCreateDTOToEntity(createLocationDTO);
        Location locationCreated = locationService.createLocation(locationToCreate, rebel);

        return locationMapper.fromEntityToDTO(locationCreated);
    }
}
