package medVoli.api.dto;

import medVoli.api.model.Consulta;
import java.time.LocalDateTime;

public record BuscarConsultasDto(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data) {

    public BuscarConsultasDto(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
