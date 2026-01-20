package io.github.cursodsousa.libraryapi.controller.dto;
import io.github.cursodsousa.libraryapi.model.Autor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Autor")
public record AutorDTO(
        UUID id,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 100, message = "campo fora do tamanho padrão")
        @Schema(name = "Nome")
        String nome,
        @NotNull(message = "campo obrigatorio")
        @Past(message = "não pode ser um data futura")
        @Schema(name = "Data nascimento")
        LocalDate dataNascimento,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 50, message = "campo fora do tamanho padrão")
        @Schema(name = "Nacionalidade")
        String nacionalidade) {
}
