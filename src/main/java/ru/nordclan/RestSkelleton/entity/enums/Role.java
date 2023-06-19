package ru.nordclan.RestSkelleton.entity.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static ru.nordclan.RestSkelleton.entity.enums.Permission.*;

/**
 * @author Shlokov Andrey
 */
public enum Role implements GrantedAuthority {
    ADMIN(Set.of(USER_SHOW_ALL, USER_SET_OPERATOR)),
    OPERATOR(Set.of(REQUEST_ACCEPT, REQUEST_DECIDE, REQUEST_SHOW_SENT_LIST)),
    USER(Set.of(REQUEST_ADD, REQUEST_SHOW_EXISTS_LIST, REQUEST_EDIT_DRAFT_LIST, REQUEST_SEND));
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}