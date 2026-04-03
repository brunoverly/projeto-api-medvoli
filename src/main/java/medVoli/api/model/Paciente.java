package medVoli.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medVoli.api.dto.AtualizarPacienteDto;
import medVoli.api.dto.CadastarPacienteDto;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nome;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private boolean ativo;


    public Paciente(CadastarPacienteDto dto){
        this.email = dto.email();
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.cpf = dto.cpf();
        this.endereco = new Endereco(dto.endereco());
        this.ativo = true;
    }

    public void deletar() {
        this.ativo = false;
    }

    public void atualizarInformacoes(@Valid AtualizarPacienteDto dto) {
        if(dto.nome() != null) this.nome = dto.nome();
        if(dto.telefone() != null) this.telefone = dto.telefone();
        if(dto.endereco() != null) this.endereco.atualizarEndereco(dto.endereco());
    }
}
