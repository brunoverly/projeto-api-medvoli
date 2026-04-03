package medVoli.api.validarConsultas;

import medVoli.api.dto.AgendarCunsultaDto;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;

@Component
public class ValidarMinutos implements ValidadorAgendamentoConsulta{
    public void validar(AgendarCunsultaDto consulta){
        var agora = LocalTime.now();
        var dataConsulta = consulta.data();

        var diferencaMinutos = Duration.between(dataConsulta, agora).toMinutes();
        if(diferencaMinutos<30){
            throw new RuntimeException("Hora ou data da consulta invalida");
        }
    }
}
