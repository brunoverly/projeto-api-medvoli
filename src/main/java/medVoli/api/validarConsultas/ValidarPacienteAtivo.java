package medVoli.api.validarConsultas;

import medVoli.api.dto.AgendarCunsultaDto;
import medVoli.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private PacienteRepository repository;

    public void validar(AgendarCunsultaDto consulta){
        var pacienteAtivo = repository.findAtivoById(consulta.idPaciente());

        if(!pacienteAtivo){
            throw new RuntimeException("Paciente não consta como ativo");
        }
    }

}
