package com.example.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "Nomination model")
public class Nomination {
    @ApiModelProperty(value = "Id номинации. При вставке генерится автоматически. NotNull.")
    private UUID nominationId;
    @ApiModelProperty(value = "Id турнира, в котором находится данная номинация. NotNull.", required = true)
    private UUID tournamentId;
    @ApiModelProperty(value = "Категория номинации.")
    private String category;
    @ApiModelProperty(value = "Название номинации.", required = true)
    private String name;
}
