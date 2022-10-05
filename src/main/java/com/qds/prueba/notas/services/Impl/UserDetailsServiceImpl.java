package com.qds.prueba.notas.services.Impl;

import com.qds.prueba.notas.daos.EstudiantesRepository;
import com.qds.prueba.notas.entity.Estudiante;
import com.qds.prueba.notas.models.EstudiantePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EstudiantesRepository estudiantesRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Estudiante estudiante = estudiantesRepository.findByUsuario(username).orElseThrow( () -> {
           throw new UsernameNotFoundException("No se encuentra el usuario requerido");
        });

        return EstudiantePrincipal.create(estudiante);
    }
}
