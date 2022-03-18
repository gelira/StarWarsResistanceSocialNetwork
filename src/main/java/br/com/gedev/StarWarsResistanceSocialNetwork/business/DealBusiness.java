package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateDealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.DealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Deal;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.DealItem;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.*;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.DealMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.DealService;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DealBusiness {
    private final RebelService rebelService;
    private final DealService dealService;
    private final DealMapper dealMapper;
    private final DealItemBusiness dealItemBusiness;

    @Transactional
    public DealDTO createDeal(CreateDealDTO createDealDTO)
            throws DealRebelTraitorException, RebelNotFoundException, InsufficientItemsException,
            InvalidItemIdException, DealItemPointsNotEquivalentException {

        Deal dealToCreate = validateDeal(createDealDTO);

        List<DealItem> dealItemsRebel1 = dealItemBusiness.validateAndAggregateDealItems(
                createDealDTO.getRebel1Items(), dealToCreate.getRebel1(), dealToCreate.getRebel2());
        List<DealItem> dealItemsRebel2 = dealItemBusiness.validateAndAggregateDealItems(
                createDealDTO.getRebel2Items(), dealToCreate.getRebel2(), dealToCreate.getRebel1());

        dealItemBusiness.validateItemPoints(dealItemsRebel1, dealItemsRebel2);

        Deal dealCreated = dealService.createDeal(dealToCreate, dealItemsRebel1, dealItemsRebel2);
        return dealMapper.fromEntityToDTO(dealCreated);
    }

    public Deal validateDeal(CreateDealDTO createDealDTO) throws RebelNotFoundException, DealRebelTraitorException {
        Deal entity = new Deal();

        entity.setRebel1(findAndCheckRebel(createDealDTO.getRebel1Id()));
        entity.setRebel2(findAndCheckRebel(createDealDTO.getRebel2Id()));

        return entity;
    }

    private Rebel findAndCheckRebel(UUID rebelId) throws DealRebelTraitorException, RebelNotFoundException {
        Rebel rebel = rebelService.findRebelByUUID(rebelId);
        if (rebel.isTraitor()) {
            throw new DealRebelTraitorException();
        }
        return rebel;
    }
}
