package medVoli.api.validarConsultas;

import medVoli.api.dto.AgendarCunsultaDto;
import medVoli.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoAtivo implements ValidadorAgendamentoConsulta{
    @Autowired
    MedicoRepository repository;

    public void validar(AgendarCunsultaDto consulta){
        if(consulta.idMedico() == null){
            return;
        }

        boolean medicoAtivo = repository.findAtivoById(consulta.idMedico());
        if(!medicoAtivo){
            throw new RuntimeException("Medico selecionado não consta como ativo");
        }
    }
}
