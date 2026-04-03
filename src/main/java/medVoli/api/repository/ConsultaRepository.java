package medVoli.api.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import medVoli.api.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {


    boolean existsByMedicoIdAndData(Long aLong, @Future LocalDateTime data);


    boolean existsByPacienteIdAndDataBetween(@NotNull Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
