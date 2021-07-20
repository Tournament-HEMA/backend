package com.example.backend.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import java.util.UUID;

@Data
@ApiModel(value = "Duel model")
public class Duel {
    @ApiModelProperty(value = "Id матча. При вставке генерится автоматически. NotNull.")
    private UUID duelId;
    @ApiModelProperty(value = "Id раунда, в котором идет данный матч. NotNull.", required = true)
    private UUID roundId;
    @ApiModelProperty(value = "Номер матча.")
    private int number;
    @ApiModelProperty(value = "Id первого участника. NotNull.", required = true)
    private UUID firstOpponent;
    @ApiModelProperty(value = "Id второго участника. NotNull.", required = true)
    private UUID secondOpponent;
    @ApiModelProperty(value = "Очки первого участника. По умолчанию - 0.")
    private int firstOpponentPoints;
    @ApiModelProperty(value = "Очки второго участника. По умолчанию - 0.")
    private int secondOpponentPoints;
}
