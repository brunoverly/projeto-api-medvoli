package medVoli.api.dto;

import medVoli.api.model.Medico;

public record BuscarMedicoDto(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade){

    public BuscarMedicoDto(Medico medico) {
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
