package medVoli.api.dto;

import medVoli.api.model.Paciente;

public record BuscarPacientesDto(
        Long id,
        String nome,
        String email,
        String cpf){

        public BuscarPacientesDto(Paciente p) {
            this(p.getId(),p.getNome(),p.getEmail(),p.getCpf());
        }

}
