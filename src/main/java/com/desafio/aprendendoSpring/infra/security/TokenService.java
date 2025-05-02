package com.desafio.aprendendoSpring.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.desafio.aprendendoSpring.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${password.jwt}")
    private String password_jwt;

    public String generateToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(this.password_jwt);
            return JWT.create()
                    .withIssuer("API Voll.medic")
                    .withSubject(usuario.getNome())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new RuntimeException("Erro ao gerar o token");
        }

    }

    public String getSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.password_jwt);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("API Voll.medic")
                    // reusable verifier instance
                    .build();

            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token jwt invalido ou expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
