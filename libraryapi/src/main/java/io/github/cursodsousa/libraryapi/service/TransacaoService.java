package io.github.cursodsousa.libraryapi.service;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import io.github.cursodsousa.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    AutorRepository autorRepository;;
    @Autowired
    LivroRepository livroRepository;

    @Transactional
    public void executar(){
        Livro livro = new Livro();
        livro.setIsbn("31231-3124");
        livro.setPreco(BigDecimal.valueOf(15.1));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("testao");
        livro.setDataPublicacao(LocalDate.of(1999, 3, 6));

        Autor autor = new Autor();
        autor.setNomeAutor("José");
        autor.setNacionalidade("Australiano");
        autor.setDataNascimento(LocalDate.of(1930, 5, 3));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);
//
//        if(autor.getNome().equals("José")){
//            throw new RuntimeException("Rollbcack!");
//        }
    }

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("dfc0d37d-981f-456d-949e-b4631ff18f54")).orElse(null);

        livro.setDataPublicacao(LocalDate.of(1800, 4, 9));

        livroRepository.save(livro);
    }

    @Transactional
    public void salvarLivroComFoto(){

    }
}
