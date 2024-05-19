package com.games.enterprisechallenge.controller;

import com.games.enterprisechallenge.model.DadosAutenticacao;
import com.games.enterprisechallenge.model.DadosTokenJWT;
import com.games.enterprisechallenge.model.Usuario;
import com.games.enterprisechallenge.repository.UsuarioRepository;
import com.games.enterprisechallenge.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @CrossOrigin
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(dados.nomeUsuario());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.nomeUsuario(), dados.senha(), usuario.getAuthorities());
            Authentication authentication = manager.authenticate(authenticationToken);
            String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
