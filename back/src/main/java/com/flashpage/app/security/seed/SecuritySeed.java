package com.flashpage.app.security.seed;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.flashpage.app.domain.model.Permiso;
import com.flashpage.app.domain.model.Usuario;
import com.flashpage.app.domain.model.Rol;
import com.flashpage.app.domain.repository.PermisoRepository;
import com.flashpage.app.domain.repository.UsuarioRepository;
import com.flashpage.app.domain.repository.RolRepository;

@Component
@Profile("dev") // en prod no sembramos nada automáticamente
public class SecuritySeed implements CommandLineRunner {

    private final PermisoRepository permisoRepository;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public SecuritySeed(
            PermisoRepository permisoRepository,
            RolRepository rolRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.permisoRepository = permisoRepository;
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // 1) Permisos base
        List<String> permisosBase = List.of(
                "USER_READ", "USER_CREATE", "USER_UPDATE", "USER_DELETE",
                "VENTA_READ", "VENTA_CREATE", "VENTA_UPDATE", "VENTA_DELETE",
                "PRODUCTO_READ", "PRODUCTO_CREATE", "PRODUCTO_UPDATE", "PRODUCTO_DELETE"
        );

        for (String codigo : permisosBase) {
            if (!permisoRepository.existsByCodigo(codigo)) {
                Permiso p = new Permiso();
                p.setCodigo(codigo);
                p.setDescripcion("Permiso: " + codigo);
                permisoRepository.save(p);
            }
        }

        // 2) Rol ADMIN (todos los permisos)
        Rol adminRol = rolRepository.findByNombre("ADMIN").orElseGet(() -> {
            Rol r = new Rol();
            r.setNombre("ADMIN");
            r.setDescripcion("Administrador del sistema");
            r.setPermisos(new HashSet<>(permisoRepository.findAll()));
            return rolRepository.save(r);
        });

        // 3) Rol USER (mínimo)
        rolRepository.findByNombre("USER").orElseGet(() -> {
            Set<Permiso> basicos = new HashSet<>();
            permisoRepository.findByCodigo("VENTA_READ").ifPresent(basicos::add);
            permisoRepository.findByCodigo("PRODUCTO_READ").ifPresent(basicos::add);

            Rol r = new Rol();
            r.setNombre("USER");
            r.setDescripcion("Usuario estándar");
            r.setPermisos(basicos);
            return rolRepository.save(r);
        });

        // 4) Usuario admin
        usuarioRepository.findByEmail("admin@local").orElseGet(() -> {
            Usuario admin = new Usuario();
            admin.setEmail("admin@local");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setActivo(true);

            Set<Rol> roles = new HashSet<>();
            roles.add(adminRol);
            admin.setRoles(roles);

            return usuarioRepository.save(admin);
        });
    }
}
