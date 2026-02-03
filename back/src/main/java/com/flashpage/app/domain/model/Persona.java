package com.flashpage.app.domain.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(unique = true, nullable = false)
    private String email;

    private String username;
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "superior_id")
    private Persona jefe;

    @OneToMany(mappedBy = "jefe")
    private List<Persona> subordinados;

    @OneToMany(mappedBy = "asesor")
    private List<Venta> ventasAsesor;

    public Persona() {}

    public Persona(Long id, String nombre, String apellido, String dni, String email, String username,
            String passwordHash, boolean activo, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.activo = activo;
        this.rol = rol;
    }

    public enum Rol {
        DUENO(5),
        GERENTE(4),
        JEFE(3),
        SUPERVISOR(2),
        EMPLEADO(1),
        CLIENTE(0);

        private final int level;
        Rol(int level) { this.level = level; }
        public int level() { return level; }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Persona getJefe() {
        return jefe;
    }

    public void setJefe(Persona jefe) {
        this.jefe = jefe;
    }

    public List<Persona> getSubordinados() {
        return subordinados;
    }

    public void setSubordinados(List<Persona> subordinados) {
        this.subordinados = subordinados;
    }

    public List<Venta> getVentasAsesor() {
        return ventasAsesor;
    }

    public void setVentasAsesor(List<Venta> ventasAsesor) {
        this.ventasAsesor = ventasAsesor;
    }
}

