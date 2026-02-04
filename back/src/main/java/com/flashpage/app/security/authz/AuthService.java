package com.flashpage.app.security.authz;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.flashpage.app.api.dto.AuthDTO;
import com.flashpage.app.security.jwt.JwtService;
import com.flashpage.app.security.user.AppUserDetails;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthDTO.LoginResponse login(AuthDTO.LoginRequest request) {
        Authentication objAuthentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        AppUserDetails objetoAppUserDetails = (AppUserDetails) objAuthentication.getPrincipal();
        String token = jwtService.generateToken(objetoAppUserDetails);

        return new AuthDTO.LoginResponse(
                token,
                objetoAppUserDetails.getPersonaId(),
                objetoAppUserDetails.getUsername(),
                objetoAppUserDetails.getRol()
        );
    }
}
