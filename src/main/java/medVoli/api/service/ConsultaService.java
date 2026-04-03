package medVoli.api.service;

import medVoli.api.dto.AgendarCunsultaDto;
import medVoli.api.dto.BuscarConsultasDto;
import medVoli.api.model.Consulta;
import medVoli.api.model.Medico;
import medVoli.api.repository.ConsultaRepository;
import medVoli.api.repository.MedicoRepository;
import medVoli.api.repository.PacienteRepository;
import medVoli.api.validarConsultas.ValidadorAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    List<ValidadorAgendamentoConsulta> validadores;

    public BuscarConsultasDto cadastrarConsulta(AgendarCunsultaDto dto){

        if(!pacienteRepository.existsById(dto.idPaciente()) ) {
            throw new RuntimeException("Id do paciente não existe");
        }
        if(dto.idMedico() != null && !medicoRepository.existsById(dto.idMedico()) ) {
            throw new RuntimeException("id medico não localizado");
        }

        validadores.forEach(v -> v.validar(dto));

        var medico = escolherMedico(dto);
        var paciente = pacienteRepository.findById(dto.idPaciente()).get();
        if(medico == null){
            throw new RuntimeException("Sem médicos disponíveis neste dia");
        }
        var consulta = new Consulta(null, medico, paciente, dto.data());
        repository.save(consulta);

        return new BuscarConsultasDto(consulta);
    }

    private Medico escolherMedico(AgendarCunsultaDto dto) {
        if(dto.idMedico() != null){
            return medicoRepository.getReferenceById(dto.idMedico());
        }
        if(dto.especialidade() == null){
            throw new RuntimeException("Especialidade ou médico devem ser informados");
        }

        return medicoRepository.escolherMedicoAleatorio(dto.especialidade(), dto.data());
    }

}
