package medVoli.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import medVoli.api.dto.AtualizarPacienteDto;
import medVoli.api.dto.BuscarPacientesDto;
import medVoli.api.dto.CadastarPacienteDto;
import medVoli.api.model.Paciente;
import medVoli.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public ResponseEntity cadastrarPaciente(@RequestBody CadastarPacienteDto dto){
            var paciente = new Paciente(dto);
            repository.save(paciente);

            return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<BuscarPacientesDto>> buscarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
        var pacientes = repository.findAllByAtivoTrue(page)
                .map(p -> new BuscarPacientesDto(p));

        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok().body(new BuscarPacientesDto(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        var pacienteBuscado = repository.getReferenceById(id);
        pacienteBuscado.deletar();

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity atualizarPaciente(@RequestBody AtualizarPacienteDto dto) {
        System.out.println(dto.id());
        var paciente = repository.getReferenceById(dto.id());
        paciente.atualizarInformacoes(dto);

        return ResponseEntity.ok().body(new BuscarPacientesDto(paciente));
    }

}
