package medVoli.api.validarConsultas;

import medVoli.api.dto.AgendarCunsultaDto;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class ValidarData implements ValidadorAgendamentoConsulta {

    public void validar(AgendarCunsultaDto consulta){
        var dataConsulta = consulta.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisFechamentoClinica = dataConsulta.getHour() > 18;

        if(domingo || antesAberturaClinica || depoisFechamentoClinica){
            throw new RuntimeException("Hora ou data da consulta invalida");
        }

    }
}
