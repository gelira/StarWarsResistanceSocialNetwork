package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.RebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.RebelMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rebels")
public class RebelController {
    private final RebelService rebelService;
    private final RebelMapper rebelMapper;

    @PostMapping
    public RebelDTO createRebel(@Valid @RequestBody CreateRebelDTO createRebelDTO) {
        Rebel rebel = rebelService.createRebel(
                rebelMapper.fromCreateDTOToEntity(createRebelDTO));
        return rebelMapper.fromEntityToRebelDTO(rebel);
    }
}
