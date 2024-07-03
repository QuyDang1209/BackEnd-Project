package com.cg.spb_houseforrent.config;

import com.cg.spb_houseforrent.model.Role;
import com.cg.spb_houseforrent.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrinciple implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1;
    private String username;
    private String password;
    private final Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(String email, String password,
                         Collection<? extends GrantedAuthority> roles) {
        this.username = email;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRole()) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        return new UserPrinciple(user.getEmail(),
                user.getPassword(),authorities);
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}