package com.example.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "Club model")
public class Club {
    @ApiModelProperty(value = "Id клуба. При вставке генерится автоматически. NotNull.")
    private UUID clubId;
    @ApiModelProperty(value = "Уникальное название клуба. NotNull.", required = true)
    private String clubname;
    @ApiModelProperty(value = "Местоположение клуба(город). Можно не указывать.")
    private String city;
    @ApiModelProperty(value = "Клуб активен/неактивен. Значение по умолчанию - true. NotNull.")
    private boolean active;
}
