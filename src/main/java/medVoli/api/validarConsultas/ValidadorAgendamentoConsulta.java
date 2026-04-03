package medVoli.api.validarConsultas;

import medVoli.api.dto.AgendarCunsultaDto;

public interface ValidadorAgendamentoConsulta {
    void validar(AgendarCunsultaDto consulta);
}
