package com.games.enterprisechallenge.configuration.security;

import com.games.enterprisechallenge.model.Aluno;
import com.games.enterprisechallenge.model.Contato;
import com.games.enterprisechallenge.model.Usuario;
import com.games.enterprisechallenge.model.Voluntario;
import com.games.enterprisechallenge.repository.AlunoRepository;
import com.games.enterprisechallenge.repository.ContatoRepository;
import com.games.enterprisechallenge.repository.VoluntarioRepository;
import com.games.enterprisechallenge.service.TokenService;
import com.games.enterprisechallenge.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private VoluntarioRepository voluntarioRepository;
    @Autowired
    private ContatoRepository contatoRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = recuperarToken(request);
        if (tokenJWT != null) {
            String subject = tokenService.getSubject(tokenJWT);
            String[] chunk = subject.split(":");
            Date expirationDate = tokenService.getExpirationDate(tokenJWT);
            Object usuario = retornaUsuario(chunk[0], chunk[1]);
            if(usuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (new JWTUtils().validateToken(chunk[0], expirationDate, usuario)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, null);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

    private Object retornaUsuario(String usuarioEmail, String roleName) {
        Object retorno = null;
        switch (roleName) {
            case "ROLE_ALUNO": {
                Optional<Aluno> aluno = alunoRepository.findByEmail(usuarioEmail);
                retorno = aluno.isPresent() ? aluno.get() : null;
                break;
            }
            case "ROLE_VOLUNTARIO": {
                Optional<Voluntario> voluntario = voluntarioRepository.findByEmail(usuarioEmail);
                retorno = voluntario.isPresent() ? voluntario.get() : null;
                break;
            }
            case "ROLE_CONTATO": {
                Optional<Contato> contato = contatoRepository.findByEmail(usuarioEmail);
                retorno = contato.isPresent() ? contato.get() : null;
                break;
            }
        }
        return retorno;
    }

}

