package br.com.biopark.cpa.config.security;

import br.com.biopark.cpa.models.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author joaoc.gama
 *
 */
@Service
public class TokenService {

    @Value("${secret.jwt}")
    private String secret;

    /**
     * Gera o token ao usuario logar no sistema
     *
     * @param usuario
     * @return
     */
    public String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("cpa")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(
                        ZoneOffset.of("-03:00")
                )).sign(Algorithm.HMAC256(secret));
    }

    /**
     * Verifica se o token enviado é valido e retorna o usuario passado no token
     * caso tudo ocorra com sucesso
     *
     * @param tokenJWT
     * @return
     */
    public String getSubject(String tokenJWT) {
        try {
            var algoritimo = Algorithm.HMAC256(secret);

            // Verifica se o token enviado esta de acordo com o issuer e o algoritimo e retorna o Subject (usuario)
            return JWT.require(algoritimo)
                    .withIssuer("cpa")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token inválido ou expirado!");
        }
    }
}