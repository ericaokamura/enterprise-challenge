package com.games.enterprisechallenge.configuration.security;

import com.games.enterprisechallenge.model.Aluno;
import com.games.enterprisechallenge.model.Contato;
import com.games.enterprisechallenge.model.Usuario;
import com.games.enterprisechallenge.model.Voluntario;
import com.games.enterprisechallenge.repository.AlunoRepository;
import com.games.enterprisechallenge.repository.ContatoRepository;
import com.games.enterprisechallenge.repository.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = recuperarToken(request);
        if (tokenJWT != null) {
            String subject = tokenService.getSubject(tokenJWT);
            Date expirationDate = tokenService.getExpirationDate(tokenJWT);
            Optional<Usuario> usuario = usuarioRepository.findByEmail(subject);
            if(usuario.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (new JWTUtils().validateToken(subject, expirationDate, usuario.get())) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.get().getAuthorities());
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

}

