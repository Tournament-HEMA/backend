package com.example.backend.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "Participant model")
public class Participant {
    @ApiModelProperty(value = "Id участника. При вставке генерится автоматически. NotNull.")
    private UUID hemaratingsId;
    @ApiModelProperty(value = "Уникальный номер участника. Автоинкримент. NotNull. Не указывать.")
    private int number;
    @ApiModelProperty(value = "Имя участника. NotNull.", required = true)
    private String firstName;
    @ApiModelProperty(value = "Фамилия участника. NotNull.", required = true)
    private String lastName;
    @ApiModelProperty(value = "Отчество участника. NotNull.", required = true)
    private String patronymic;
    @ApiModelProperty(value = "Id клуба, в котором состоит участник.")
    private UUID clubId;
}
