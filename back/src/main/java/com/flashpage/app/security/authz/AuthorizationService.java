package com.flashpage.app.security.authz;

import org.springframework.stereotype.Service;

import com.flashpage.app.domain.model.Persona.Rol;

@Service("authz")
public class AuthorizationService {

  // CRUD sobre personas (empleados/clientes): solo si soy superior
  public boolean puedeCrudPersona(Rol actor, Rol target) {
    if (actor == null || target == null) return false;
    return actor.level() > target.level();
  }

  // CRUD productos: JEFE o superior
  public boolean puedeCrudProducto(Rol actor) {
    return actor != null && actor.level() >= Rol.JEFE.level();
  }

  // CRUD ventas: EMPLEADO o superior (si querés que arriba también pueda)
  public boolean puedeCrudVenta(Rol actor) {
    return actor != null && actor.level() >= Rol.EMPLEADO.level();
  }
}
