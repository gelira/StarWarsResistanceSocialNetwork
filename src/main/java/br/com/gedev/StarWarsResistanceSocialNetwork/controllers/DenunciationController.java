package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.business.DenunciationBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateDenunciationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.DenunciationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Denunciation;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.AccusedRebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.AccuserRebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.AutoDenunciationException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.RepeatedDenunciationException;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.DenunciationMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.DenunciationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/denunciations")
public class DenunciationController {
    private final DenunciationBusiness denunciationBusiness;
    private final DenunciationService denunciationService;
    private final DenunciationMapper denunciationMapper;

    @PostMapping
    public DenunciationDTO createDenunciation(@Valid @RequestBody CreateDenunciationDTO createDenunciationDTO)
            throws AccusedRebelNotFoundException, AutoDenunciationException,
                AccuserRebelNotFoundException, RepeatedDenunciationException {

        Denunciation denunciationToCreate = denunciationBusiness.validateDenunciation(createDenunciationDTO);
        Denunciation denunciationCreated = denunciationService.createDenunciation(denunciationToCreate);
        return denunciationMapper.fromEntityToDTO(denunciationCreated);
    }
}
