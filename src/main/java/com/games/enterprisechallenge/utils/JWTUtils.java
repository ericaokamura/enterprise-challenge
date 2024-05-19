package com.games.enterprisechallenge.utils;

import com.games.enterprisechallenge.model.Usuario;
import com.games.enterprisechallenge.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTUtils {

    @Autowired
    private TokenService tokenService;

    public boolean validateToken(String subject, Date expirationDate, Usuario usuario) {
        return subject.equals(usuario.getUsername()) && !isTokenExpired(expirationDate);
    }

    private boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }
}
