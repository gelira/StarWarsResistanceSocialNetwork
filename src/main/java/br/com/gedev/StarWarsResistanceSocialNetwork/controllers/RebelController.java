package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.business.LocationBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.business.RebelBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.*;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.InvalidItemIdException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.RebelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rebels")
public class RebelController {
    private final RebelBusiness rebelBusiness;
    private final LocationBusiness locationBusiness;

    @GetMapping
    public Page<RebelDTO> listAllRebels(
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            }) Pageable pageable) {
        return rebelBusiness.listAllRebels(pageable);
    }

    @PostMapping
    public RebelDTO createRebel(@Valid @RequestBody CreateRebelDTO createRebelDTO) throws InvalidItemIdException {
        return rebelBusiness.createRebel(createRebelDTO);
    }

    @PostMapping("{id}/locations")
    public LocationDTO createLocation(
            @PathVariable("id") UUID rebelId, @Valid @RequestBody CreateLocationDTO createLocationDTO)
            throws RebelNotFoundException {

        return locationBusiness.createNewLocation(rebelId, createLocationDTO);
    }
}
