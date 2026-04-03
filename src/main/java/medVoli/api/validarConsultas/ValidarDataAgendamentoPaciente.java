package medVoli.api.validarConsultas;

import medVoli.api.dto.AgendarCunsultaDto;
import medVoli.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarDataAgendamentoPaciente implements ValidadorAgendamentoConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(AgendarCunsultaDto consulta){
        var primeiroHorario = consulta.data().withHour(7);
        var ultimoHorario = consulta.data().withHour(18);
        var pacientePossueAgendamento = repository.existsByPacienteIdAndDataBetween(consulta.idPaciente(),primeiroHorario,ultimoHorario);
        if(pacientePossueAgendamento){
            throw  new RuntimeException("Paciente possui agendamento");
        }
    }

}
