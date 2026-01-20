package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

        List<Autor> findByNomeAutor(String nomeAutor);
        List<Autor> findByNacionalidade(String nacionalidade);
        List<Autor> findByNomeAutorAndNacionalidade(String nomeAutor, String nacionalidade);
        Optional<Autor> findByNomeAutorAndDataNascimentoAndNacionalidade(String nomeAutor, LocalDate dataNascimento, String nacionalidade);
}
