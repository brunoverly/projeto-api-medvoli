package medVoli.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import medVoli.api.dto.LoginDto;
import medVoli.api.model.Usuario;
import medVoli.api.repository.UsuarioRepository;
import medVoli.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AtutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginDto login){
        var token = new UsernamePasswordAuthenticationToken(login.login(), login.senha());
        var autenticacao = authenticationManager.authenticate(token);

        return ResponseEntity.ok(tokenService.gerarToken((Usuario) autenticacao.getPrincipal()));
    }

    @PostMapping("/cadastro")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid LoginDto dto){
        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        usuarioRepository.save(new Usuario(dto.login(), senhaCriptografada));

        return ResponseEntity.ok().build();
    }
}
