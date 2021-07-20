package com.example.backend.model;

import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRoleRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@ApiModel(value = "User model")
public class User implements UserDetails {
    @ApiModelProperty(value = "Id пользователя. При вставке генерится автоматически. NotNull.")
    private UUID id;
    @ApiModelProperty(value = "Уникальный логин пользователя. NotNull.", required = true)
    private String username;
    @ApiModelProperty(value = "Пароль пользователя. NotNull.", required = true)
    private String password;
    @ApiModelProperty(value = "Имя пользователя. NotNull.", required = true)
    private String firstName;
    @ApiModelProperty(value = "Фамилия пользователя. NotNull.", required = true)
    private String lastName;
    @ApiModelProperty(value = "Отчество пользователя.")
    private String patronymic;
    private Set<Role> roles;

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }
}
