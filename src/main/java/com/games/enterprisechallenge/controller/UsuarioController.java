package com.games.enterprisechallenge.controller;

import com.games.enterprisechallenge.model.dto.UsuarioDTO;
import com.games.enterprisechallenge.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO dto = usuarioService.salvarUsuario(usuarioDTO);
            return ResponseEntity.ok(dto);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}