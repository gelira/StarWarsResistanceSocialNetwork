package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.business.DealBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.business.DealItemBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateDealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.DealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Deal;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.DealItem;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.*;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.DealMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deals")
public class DealController {
    private final DealBusiness dealBusiness;
    private final DealItemBusiness dealItemBusiness;
    private final DealService dealService;
    private final DealMapper dealMapper;

    @PostMapping
    public DealDTO createDeal(@Valid @RequestBody CreateDealDTO createDealDTO)
            throws DealRebelTraitorException, RebelNotFoundException, InsufficientItemsException,
            InvalidItemIdException, DealItemPointsNotEquivalentException {

        Deal dealToCreate = dealBusiness.validateDeal(createDealDTO);

        List<DealItem> dealItemsRebel1 = dealItemBusiness.validateAndAggregateDealItems(
                createDealDTO.getRebel1Items(), dealToCreate.getRebel1(), dealToCreate.getRebel2());
        List<DealItem> dealItemsRebel2 = dealItemBusiness.validateAndAggregateDealItems(
                createDealDTO.getRebel2Items(), dealToCreate.getRebel2(), dealToCreate.getRebel1());

        dealItemBusiness.validateItemPoints(dealItemsRebel1, dealItemsRebel2);

        Deal dealCreated = dealService.createDeal(dealToCreate, dealItemsRebel1, dealItemsRebel2);
        return dealMapper.fromEntityToDTO(dealCreated);
    }
}
