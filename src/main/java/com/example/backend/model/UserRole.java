package com.example.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "UserRole model")
public class UserRole {
    @ApiModelProperty(value = "Id роли пользователя. При вставке генерится автоматически. NotNull.")
    private UUID id;
    @ApiModelProperty(value = "Id пользователя. Нужно указать id существующего пользователя. NotNull.", required = true)
    private UUID userId;
    @ApiModelProperty(value = "Id роли. Нужно указать id существующей роли. NotNull.", required = true)
    private UUID roleId;
}
