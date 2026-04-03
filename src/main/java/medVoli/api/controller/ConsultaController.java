package medVoli.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import medVoli.api.dto.AgendarCunsultaDto;
import medVoli.api.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @PostMapping
    public ResponseEntity agendarContulta(@RequestBody AgendarCunsultaDto consulta){
        return ResponseEntity.ok(service.cadastrarConsulta(consulta));
    }
}
