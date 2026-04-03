package medVoli.api.dto;

import medVoli.api.model.Endereco;
import medVoli.api.model.Medico;

public record MedicoDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco) {

    public MedicoDto(Medico medico) {
        this(medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }
}
