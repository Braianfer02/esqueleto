package com.flashpage.app.security.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.flashpage.app.domain.model.Persona;

public class AppUserDetails implements UserDetails {

    private final Persona persona;

    public AppUserDetails(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public Long getPersonaId() {
        return persona.getId();
    }

    public Persona.Rol getRol() {
        return persona.getRol();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security espera "ROLE_X"
        return List.of(new SimpleGrantedAuthority("ROLE_" + persona.getRol().name()));
    }

    @Override
    public String getPassword() {
        // en Persona guardás passwordHash
        return persona.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return persona.getUsername();
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
        return persona.getActivo();
    }
}
