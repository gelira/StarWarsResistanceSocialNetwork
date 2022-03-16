package br.com.gedev.StarWarsResistanceSocialNetwork.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RebelNameDTO {
    private UUID _id;
    private String name;
}
