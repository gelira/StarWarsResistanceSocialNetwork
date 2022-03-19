package br.com.gedev.StarWarsResistanceSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportDTO {
    @JsonProperty("rebels_percentage")
    private Double rebelsPercentage;

    @JsonProperty("traitors_percentage")
    private Double traitorsPercentage;

    @JsonProperty("average_items")
    private List<ItemAverageDTO> averageItems;

    @JsonProperty("points_lost_by_traitors")
    private Integer pointsLostByTraitors;
}
