package ru.nordclan.RestSkelleton.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.nordclan.RestSkelleton.entity.User;
import ru.nordclan.RestSkelleton.entity.enums.Role;

import java.util.Collection;
import java.util.Set;

@Data
public class UserDTO implements UserDetails {

    private Integer id;

    private Set<Role> roles;

    private String email;

    private String password;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.id = user.getId();
        this.roles = user.getRoles();
        this.password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
