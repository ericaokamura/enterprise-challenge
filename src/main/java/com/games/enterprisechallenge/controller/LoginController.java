package com.games.enterprisechallenge.controller;

import com.games.enterprisechallenge.model.DadosAutenticacao;
import com.games.enterprisechallenge.model.DadosTokenJWT;
import com.games.enterprisechallenge.model.Usuario;
import com.games.enterprisechallenge.repository.AlunoRepository;
import com.games.enterprisechallenge.repository.ContatoRepository;
import com.games.enterprisechallenge.repository.VoluntarioRepository;
import com.games.enterprisechallenge.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    private AlunoRepository alunoRepository;
    @Autowired
    private VoluntarioRepository voluntarioRepository;
    @Autowired
    private ContatoRepository contatoRepository;

    @PostMapping
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        Optional<Usuario> usuarioOptional = retornaUsuario(dados.nomeUsuario(), dados.roleName());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if(BCrypt.checkpw(dados.senha(), usuario.getSenha())) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.nomeUsuario(), dados.senha(), usuario.getAuthorities());
                Authentication authentication = manager.authenticate(authenticationToken);
                String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
                return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Optional<Usuario> retornaUsuario(String usuarioEmail, String roleName) {
        Optional usuario = Optional.empty();
        switch (roleName) {
            case "ROLE_ALUNO": usuario = alunoRepository.findByEmail(usuarioEmail); break;
            case "ROLE_VOLUNTARIO": usuario = voluntarioRepository.findByEmail(usuarioEmail); break;
            case "ROLE_CONTATO": usuario = contatoRepository.findByEmail(usuarioEmail); break;
        }
        return usuario;
    }
}
