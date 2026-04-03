package medVoli.api.validarConsultas;

import medVoli.api.dto.AgendarCunsultaDto;
import medVoli.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoComConsultaConflitante implements ValidadorAgendamentoConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(AgendarCunsultaDto consulta){
        var medicoPossueConsulta = repository.existsByMedicoIdAndData(consulta.idMedico(),consulta.data());
        if(medicoPossueConsulta){
            throw new RuntimeException("Medico com consulta inexistente");
        }

    }
}
