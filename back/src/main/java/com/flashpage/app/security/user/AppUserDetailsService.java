package com.flashpage.app.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flashpage.app.domain.model.Persona;
import com.flashpage.app.domain.repository.PersonaRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final PersonaRepository personaRepository;

    public AppUserDetailsService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona persona = personaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new AppUserDetails(persona);
    }
}
