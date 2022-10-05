package com.qds.prueba.notas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qds.prueba.notas.entity.Estudiante;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EstudiantePrincipal implements UserDetails {
    private Long id;

    private String nombres;

    private String apellidos;
    private String usuario;
    @JsonIgnore
    private String clave;

    private Collection<? extends GrantedAuthority> roles;


    public static EstudiantePrincipal create(Estudiante est) {

        GrantedAuthority authority = new SimpleGrantedAuthority(est.getRol());

        return new EstudiantePrincipal(
                est.getId(),
                est.getNombres(),
                est.getApellidos(),
                est.getUsuario(),
                est.getClave(),
                List.of(authority)
        );
    }

    public Long getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }
    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstudiantePrincipal that = (EstudiantePrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
