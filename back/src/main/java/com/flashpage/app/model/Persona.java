package com.flashpage.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Persona {

    // Le asignamos ID unico
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Colocamos datos personales
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;
    
    @Column(nullable = false, unique = true)    
    private String dni;
    private String telefono;
    
    // Colocamos su rol (Controller)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
    
    // Asignamos relaciones
    @OneToMany(mappedBy = "asesor", fetch = FetchType.LAZY)
    private List<Venta> ventas;
        
    // Colocamos datos personales de su direccion 
    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String localidad;

    @Column(nullable = false)
    private String provincia;

    @Column(nullable = false)
    private String codigoPostal;

    @Column(nullable = false)
    private String pais;
    
    // Asignamos usuario y contraseña
    @Column(nullable = false)
    private String usuario;
    
    @Column(nullable = false)
    private String password;

    // Creamos: Constructor vacio, Constructor con parametros, Getter y Setter
    public Persona() {
    }
    
    public Persona(Long id, String nombre, String apellido, String dni, String telefono, Rol rol, List<Venta> ventas,
            String direccion, String localidad, String provincia, String codigoPostal, String pais, String usuario,
            String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.rol = rol;
        this.ventas = ventas;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.usuario = usuario;
        this.password = password;
    }

    public enum Rol{
        Dueño,
        Gerente,
        Jefe,
        Supervisor,
        Asesor,
        Cliente,
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
