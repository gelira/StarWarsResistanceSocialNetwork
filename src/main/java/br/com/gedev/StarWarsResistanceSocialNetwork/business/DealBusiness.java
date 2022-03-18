package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateDealDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Deal;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.DealRebelTraitorException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.RebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DealBusiness {
    private final RebelService rebelService;

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
