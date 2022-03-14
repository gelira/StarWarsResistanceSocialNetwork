package br.com.gedev.StarWarsResistanceSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RebelDTO {
    private UUID _id;
    private String name;
    private Integer age;
    private String genre;

    @JsonProperty("accused_count")
    private Integer accusedCount;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    private LocationDTO location;
    private List<ItemRebelDTO> inventory;
}
