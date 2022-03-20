package br.com.gedev.StarWarsResistanceSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class DealItemDTO {
    private UUID _id;
    private Integer quantity;

    @JsonProperty("item_id")
    private UUID itemId;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("source_rebel")
    private RebelNameDTO sourceRebel;

    @JsonProperty("receiver_rebel")
    private RebelNameDTO receiverRebel;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;
}
