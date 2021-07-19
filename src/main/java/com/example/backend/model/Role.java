package com.example.backend.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Data
public class Role implements GrantedAuthority {
    private UUID id;
    private String name;

    @Override
    public String getAuthority(){
        return getName();
    }
}
