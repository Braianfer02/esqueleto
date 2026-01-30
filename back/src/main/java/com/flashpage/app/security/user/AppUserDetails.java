package com.flashpage.app.security.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.flashpage.app.domain.model.Permiso;
import com.flashpage.app.domain.model.Persona;
import com.flashpage.app.domain.model.Rol;

public class AppUserDetails implements UserDetails {

    private final Persona persona;
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public AppUserDetails(Persona persona) {
        this.persona = persona;
        mapAuthorities(persona);
    }

    private void mapAuthorities(Persona persona) {
        if (persona.getRoles() == null) return;

        for (Rol rol : persona.getRoles()) {
            // rol como ROLE_*
            if (rol.getNombre() != null && !rol.getNombre().isBlank()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
            }

            // permisos como authorities
            if (rol.getPermisos() == null) continue;
            for (Permiso p : rol.getPermisos()) {
                if (p.getCodigo() != null && !p.getCodigo().isBlank()) {
                    authorities.add(new SimpleGrantedAuthority(p.getCodigo()));
                }
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return persona.getPassword();
    }

    @Override
    public String getUsername() {
        return persona.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(persona.getActivo());
    }

    public Persona getPersona() {
        return persona;
    }
}
