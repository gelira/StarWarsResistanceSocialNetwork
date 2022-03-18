package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.business.DealBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateDealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.DealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deals")
public class DealController {
    private final DealBusiness dealBusiness;

    @PostMapping
    public DealDTO createDeal(@Valid @RequestBody CreateDealDTO createDealDTO)
            throws DealRebelTraitorException, RebelNotFoundException, InsufficientItemsException,
            InvalidItemIdException, DealItemPointsNotEquivalentException, AutoDealException {

        return dealBusiness.createDeal(createDealDTO);
    }
}
