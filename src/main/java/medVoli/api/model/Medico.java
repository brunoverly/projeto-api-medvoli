package medVoli.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medVoli.api.dto.AtualizarMedico;
import medVoli.api.dto.CadastarMedicoDto;
import medVoli.api.dto.Especialidade;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private boolean ativo;


    public Medico(CadastarMedicoDto dto){
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.crm = dto.crm();
        this.especialidade = dto.especialidade();
        this.endereco = new Endereco(dto.endereco());
        this.ativo = true;
    }
    public void atualizarInformacoes(@Valid AtualizarMedico dto) {
        if(dto.nome() != null) this.nome = dto.nome();
        if(dto.telefone() != null) this.telefone = dto.telefone();
        if(dto.endereco() != null) this.endereco.atualizarEndereco(dto.endereco());
    }

    public void excluir() {
        this.ativo = false;
    }
}
