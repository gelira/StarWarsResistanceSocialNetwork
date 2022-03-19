package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.business.DealBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateDealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.DealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deals")
public class DealController {
    private final DealBusiness dealBusiness;

    @GetMapping
    public List<DealDTO> listAllDeals() {
        return dealBusiness.listAllDeals();
    }

    @PostMapping
    public DealDTO createDeal(@Valid @RequestBody CreateDealDTO createDealDTO)
            throws DealRebelTraitorException, RebelNotFoundException, InsufficientItemsException,
            InvalidItemIdException, DealItemPointsNotEquivalentException, AutoDealException {

        return dealBusiness.createDeal(createDealDTO);
    }
}
