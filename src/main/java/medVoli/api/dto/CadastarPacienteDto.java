package medVoli.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medVoli.api.model.Endereco;

public record CadastarPacienteDto(
        @NotBlank
        String email,

        @NotBlank
        String nome,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{11}")

        String cpf,

        @NotNull
        EnderecoDto endereco){
}
