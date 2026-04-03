package medVoli.api.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank
        String login,

        @NotBlank
        String senha) {

}
