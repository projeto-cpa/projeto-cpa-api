package br.com.biopark.cpa.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 *
 * @author joao.gama
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {


    /**
     * Faz o filtro da requisição para ver se o usuario possui autorização
     * para prosseguir com a utilização das requisições
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        filterChain.doFilter(request, response);
    }

    /**
     * Retorna o token do usuario que vem do header da requisição
     *
     * @param request
     * @return
     */
    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null) {
            throw new RuntimeException("Token não enviado");
        }

        return authorizationHeader.replace("Bearer", "");
    }
}
