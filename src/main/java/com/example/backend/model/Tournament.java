package com.example.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@ApiModel(value = "Tournament model")
public class Tournament {
    @ApiModelProperty(value = "Id турнира. При вставке генерится автоматически. NotNull.")
    private UUID tournamentId;
    @ApiModelProperty(value = "Уникальное название турнира. NotNull.", required = true)
    private String name;
    @ApiModelProperty(value = "Дата начала турнира.")
    private Date beginning;
    @ApiModelProperty(value = "Дата окончания турнира.")
    private Date ending;
}
