package com.example.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "Round model")
public class Round {
    @ApiModelProperty(value = "Id раунда. При вставке генерится автоматически. NotNull.")
    private UUID roundId;
    @ApiModelProperty(value = "Id номинации, в которой находится данный раунд. NotNull.", required = true)
    private UUID nominationId;
    @ApiModelProperty(value = "Номер раунда. Можно не указывать.")
    private int number;
    @ApiModelProperty(value = "Название раунда. По умолчанию - Раунд.")
    private String name;
}
