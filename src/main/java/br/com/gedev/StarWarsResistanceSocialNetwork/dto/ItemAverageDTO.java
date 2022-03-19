package br.com.gedev.StarWarsResistanceSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ItemAverageDTO {
    @JsonProperty("item_id")
    private UUID itemId;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("item_count")
    private Integer itemCount;

    @JsonProperty("rebel_count")
    private Integer rebelCount;

    @JsonProperty("average_per_rebel")
    private Double averagePerRebel;
}
