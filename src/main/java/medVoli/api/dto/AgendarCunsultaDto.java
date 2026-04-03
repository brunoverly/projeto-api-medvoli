package medVoli.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendarCunsultaDto(
        Long idMedico,
        Especialidade especialidade,
        @NotNull
        Long idPaciente,
        @Future
        LocalDateTime data
) {
}
