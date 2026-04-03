package medVoli.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medVoli.api.dto.AtualizarMedico;
import medVoli.api.dto.BuscarMedicoDto;
import medVoli.api.dto.CadastarMedicoDto;
import medVoli.api.dto.MedicoDto;
import medVoli.api.model.Medico;
import medVoli.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    public ResponseEntity cadastrarMedico(@RequestBody @Valid CadastarMedicoDto dto, UriComponentsBuilder uriBuilder){
        var medico =new Medico(dto);
        repository.save(medico);
        var uri = uriBuilder.path("medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoDto(medico));
    }

    @GetMapping
    public ResponseEntity<Page<BuscarMedicoDto>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"})Pageable pageable){

        var page = repository.findAllByAtivoTrue(pageable).map(BuscarMedicoDto::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new MedicoDto(medico));

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid AtualizarMedico dto){
        var medico = repository.getReferenceById(dto.id());
        medico.atualizarInformacoes(dto);

        return ResponseEntity.ok().body(new MedicoDto(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }
}
