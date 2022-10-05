package com.qds.prueba.notas.controller;

import com.qds.prueba.notas.config.CurrentUser;
import com.qds.prueba.notas.entity.Estudiante;
import com.qds.prueba.notas.entity.Nota;
import com.qds.prueba.notas.models.EstudiantePrincipal;
import com.qds.prueba.notas.models.TokenInfo;
import com.qds.prueba.notas.services.Impl.JwtUtilService;
import com.qds.prueba.notas.services.NotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class NotasController {

    @Autowired
    private NotasService notasService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/obtenerNotas/{idAlumno}")
    public ResponseEntity<Map<String,Object>> obtenerNotasxAlumno(@PathVariable Long idAlumno, @CurrentUser EstudiantePrincipal estudiante){
        if(estudiante.getId().equals(idAlumno)){
            return new ResponseEntity<>(notasService.listarNotas(idAlumno), HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(Collections.singletonMap("response","El token no hace referencia al estudiante"), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/guardarNota/{idAlumno}")
    public ResponseEntity<Map<String,Object>> guardarNotaAlumno(
            @PathVariable Long idAlumno,
            @RequestBody List<Nota> notas,
            @CurrentUser EstudiantePrincipal estudiante){
        System.out.println(estudiante.getRoles());
        return new ResponseEntity<>(notasService.guardarNotas(idAlumno, notas), HttpStatus.ACCEPTED);
    }

    @GetMapping("/obtenerToken")
    public ResponseEntity<TokenInfo> obtenerToken(@RequestBody Estudiante estudiante){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(estudiante.getUsuario(),
                        estudiante.getClave()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(
                estudiante.getUsuario());

        String jwt = jwtUtilService.generateToken(userDetails);

        TokenInfo tokenInfo = new TokenInfo(jwt);

        return ResponseEntity.ok(tokenInfo);
    }
}
