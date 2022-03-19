package br.com.gedev.StarWarsResistanceSocialNetwork.controllers;

import br.com.gedev.StarWarsResistanceSocialNetwork.business.ItemBusiness;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemBusiness itemBusiness;

    @GetMapping
    public List<ItemDTO> listAllItems() {
        return itemBusiness.listAllItems();
    }
}
