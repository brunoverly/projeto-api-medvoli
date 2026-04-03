package medVoli.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import medVoli.api.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario){
        try {
            //define o tipo de encoder
            var algoritmo = Algorithm.HMAC256("12345678");
            //define o que irá no token
            return JWT.create()
                    .withIssuer("API med_vol")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public String recuperarLogin(String token) {
        try {
            var algoritmo = Algorithm.HMAC256("12345678");
            return JWT.require(algoritmo)
                    // specify any specific claim validations
                    .withIssuer("API med_vol")
                    // reusable verifier instance
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token invalido ou expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
