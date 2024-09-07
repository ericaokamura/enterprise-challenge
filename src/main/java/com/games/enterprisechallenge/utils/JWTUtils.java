package com.games.enterprisechallenge.utils;

import com.games.enterprisechallenge.model.Aluno;
import com.games.enterprisechallenge.model.Contato;
import com.games.enterprisechallenge.model.Usuario;
import com.games.enterprisechallenge.model.Voluntario;
import com.games.enterprisechallenge.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTUtils {

    @Autowired
    private TokenService tokenService;

    public boolean validateToken(String subject, Date expirationDate, Object usuario) {
        if(usuario instanceof Aluno) {
            return subject.equals(((Aluno) usuario).getEmail()) && !isTokenExpired(expirationDate);
        } else if (usuario instanceof Voluntario) {
            return subject.equals(((Voluntario) usuario).getEmail()) && !isTokenExpired(expirationDate);
        } else if (usuario instanceof Contato) {
            return subject.equals(((Contato) usuario).getEmail()) && !isTokenExpired(expirationDate);
        }
        return false;
    }

    private boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }
}
