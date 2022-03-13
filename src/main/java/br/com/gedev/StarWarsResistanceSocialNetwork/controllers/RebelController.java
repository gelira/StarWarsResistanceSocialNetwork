package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.RebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
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

    @PostMapping
    public RebelDTO createRebel(@Valid @RequestBody CreateRebelDTO createRebelDTO) {
        Rebel toCreate = new Rebel();
        toCreate.setName(createRebelDTO.getName());
        toCreate.setAge(createRebelDTO.getAge());
        toCreate.setGenre(createRebelDTO.getGenre());

        Rebel created = rebelService.createRebel(toCreate);

        RebelDTO dto = new RebelDTO();
        dto.set_id(created.get_id());
        dto.setAge(created.getAge());
        dto.setGenre(created.getGenre());
        dto.setName(created.getName());
        dto.setAccusedCount(created.getAccusedCount());
        dto.setCreatedAt(created.getCreatedAt());
        dto.setUpdatedAt(created.getUpdatedAt());

        return dto;
    }
}
