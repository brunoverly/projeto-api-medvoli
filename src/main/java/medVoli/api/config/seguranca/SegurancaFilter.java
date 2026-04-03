package medVoli.api.config.seguranca;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import medVoli.api.repository.UsuarioRepository;
import medVoli.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SegurancaFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository repository;
    @Autowired
    private Autenticacao autenticacao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Iniciando SegurancaFilter");
        var tokenJwt = recuperarToken(request);
        if(tokenJwt != null){
            var login = tokenService.recuperarLogin(tokenJwt);
            var usuario = repository.findByLogin(login);

            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }

        System.out.println("Fim SegurancaFilter");
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        System.out.println("Iniciando verificacao de token");
        var authorizationRequest = request.getHeader("Authorization");
        if(authorizationRequest != null) {
            return authorizationRequest.replace("Bearer ", "");
        }
        return null;
    }
}
