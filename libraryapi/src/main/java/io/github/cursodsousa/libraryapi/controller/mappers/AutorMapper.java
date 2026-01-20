package io.github.cursodsousa.libraryapi.controller.mappers;

import io.github.cursodsousa.libraryapi.controller.dto.AutorDTO;
import io.github.cursodsousa.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(source = "nome", target = "nomeAutor")
    Autor toEntity(AutorDTO dto);

    @Mapping(source = "nomeAutor", target = "nome")
    AutorDTO toDto(Autor autor);
}
