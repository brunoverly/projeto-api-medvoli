package medVoli.api.repository;

import jakarta.validation.constraints.Future;
import medVoli.api.dto.Especialidade;
import medVoli.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
        SELECT m from Medico m WHERE m.ativo = true AND m.especialidade = :especialidade 
        and m.id not in(
        select c.medico.id from Consulta c where c.data = :data) 
        order by rand() limit 1
        """)
    Medico escolherMedicoAleatorio(Especialidade especialidade, @Future LocalDateTime data);

    @Query("""
    select m.ativo from Medico m where m.id = :idMedico
    """)
    boolean findAtivoById(Long idMedico);
}
