package com.example.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Data
@ApiModel(value = "Role model")
public class Role implements GrantedAuthority {
    @ApiModelProperty(value = "Id клуба. При вставке генерится автоматически. NotNull.")
    private UUID id;
    @ApiModelProperty(value = "Уникальное название клуба. NotNull.", required = true)
    private String name;

    @Override
    public String getAuthority(){
        return getName();
    }
}
