package com.flashpage.app.security.authz;

import org.springframework.stereotype.Service;

import com.flashpage.app.domain.model.Persona;
import com.flashpage.app.domain.model.Persona.Rol;
import com.flashpage.app.domain.model.Venta;

@Service
public class AuthorizationService {

    // CREATE: EMPLEADO solo para sí; SUPERVISOR+ puede crear para cualquiera
    public boolean puedeCrearVenta(Persona actor, Persona asesorTarget) {
        if (actor == null || asesorTarget == null) return false;

        if (actor.getRol() == Rol.EMPLEADO) {
            return actor.getId().equals(asesorTarget.getId());
        }
        return actor.getRol().level() >= Rol.SUPERVISOR.level();
    }

    // UPDATE: dueño puede (si querés), o cadena jerárquica, o dueño(DUENO)
    public boolean puedeActualizarVenta(Persona actor, Venta venta) {
        if (actor == null || venta == null || venta.getAsesor() == null) return false;

        Persona asesor = venta.getAsesor();
        if (actor.getId().equals(asesor.getId())) return true;
        if (actor.getRol() == Rol.DUENO) return true;

        return esJefeDirectoOIndirecto(actor, asesor);
    }

    // CANCEL (soft delete): asesor NO; DUENO sí; cadena jerárquica sí
    public boolean puedeCancelarVenta(Persona actor, Venta venta) {
        if (actor == null || venta == null || venta.getAsesor() == null) return false;

        Persona asesor = venta.getAsesor();

        if (actor.getId().equals(asesor.getId())) return false;
        if (actor.getRol() == Rol.DUENO) return true;

        return esJefeDirectoOIndirecto(actor, asesor);
    }

    public boolean esJefeDirectoOIndirecto(Persona posibleJefe, Persona empleado) {
        Persona actual = empleado.getJefe();
        while (actual != null) {
            if (actual.getId().equals(posibleJefe.getId())) return true;
            actual = actual.getJefe();
        }
        return false;
    }
}
