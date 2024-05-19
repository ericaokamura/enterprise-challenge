package com.games.enterprisechallenge.utils;

import com.games.enterprisechallenge.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTUtils {

    @Autowired
    private TokenService tokenService;

    public boolean validateToken(String token, String subject, Date expirationDate, UserDetails userDetails) {
        return subject.equals(userDetails.getUsername()) && !isTokenExpired(token, expirationDate);
    }

    private boolean isTokenExpired(String token, Date expirationDate) {
        return expirationDate.before(new Date());
    }
}
