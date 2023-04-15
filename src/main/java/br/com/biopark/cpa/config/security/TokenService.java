package br.com.biopark.cpa.config.security;

import br.com.biopark.cpa.models.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("cpa")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(
                        ZoneOffset.of("-03:00")
                )).sign(Algorithm.HMAC256("secret"));
    }
}