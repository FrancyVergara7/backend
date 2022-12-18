package com.dh.login.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
public class Usuario implements UserDetails {
    @Id
    @SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    @Column(name = "id", nullable = false)
    private Long id;
    private String nombre;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String nombre, String email, String username, String password, Rol usuarioRoles) {
        this.nombre = nombre;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rol = usuarioRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new  SimpleGrantedAuthority(rol.name());
        return Collections.singletonList(grantedAuthority);
    }

    public Usuario(String email, String s, String password, Set<GrantedAuthority> grantList) {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
