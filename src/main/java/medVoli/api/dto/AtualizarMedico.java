package medVoli.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarMedico(
                @NotNull
                Long id,
                String telefone,
                EnderecoDto endereco,
                String nome){
}
