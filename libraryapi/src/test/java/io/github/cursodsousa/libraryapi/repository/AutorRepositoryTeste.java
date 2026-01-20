package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTeste {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvartest(){
        Autor autor = new Autor();
        autor.setNomeAutor("João");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1980, 3, 30));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("38635ff0-4992-4b91-bcaa-946460ca7d27");
        Optional<Autor> possivelAutor = autorRepository.findById(id);
        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1900, 3, 2));

            autorRepository.save(autorEncontrado);

        }
    }

    @Test
    public void listarTest(){
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("contagem de autores " + autorRepository.count());
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("38635ff0-4992-4b91-bcaa-946460ca7d27");

        autorRepository.deleteById(id);
    }

    @Test
    public void salvarAutorLivroTest(){
        Autor autor = new Autor();
        autor.setNomeAutor("Pedro");
        autor.setNacionalidade("canadense");
        autor.setDataNascimento(LocalDate.of(2004, 5, 9));

        Livro livro = new Livro();
        livro.setIsbn("12312-3124");
        livro.setPreco(BigDecimal.valueOf(17.1));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("aaaaaa");
        livro.setDataPublicacao(LocalDate.of(2010, 2, 7));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("55777-3124");
        livro2.setPreco(BigDecimal.valueOf(14.1));
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setTitulo("bbbbbbb");
        livro2.setDataPublicacao(LocalDate.of(2014, 6, 27));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    public void salvarAutorLivroTestCascade(){
        Autor autor = new Autor();
        autor.setNomeAutor("Pedro");
        autor.setNacionalidade("canadense");
        autor.setDataNascimento(LocalDate.of(2004, 5, 9));

        Livro livro = new Livro();
        livro.setIsbn("12312-3124");
        livro.setPreco(BigDecimal.valueOf(17.1));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("aaaaaa");
        livro.setDataPublicacao(LocalDate.of(2010, 2, 7));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("55777-3124");
        livro2.setPreco(BigDecimal.valueOf(14.1));
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setTitulo("bbbbbbb");
        livro2.setDataPublicacao(LocalDate.of(2014, 6, 27));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

//        livroRepository.saveAll(autor.getLivros());
    }

    @Test
//    @Transactional
    public void buscarLivrosAutor(){
        var autor = autorRepository.findById(UUID.fromString("ad425bc2-f512-48ae-a2d5-7de77ec50232")).get();

        List<Livro> livros = livroRepository.findByAutor(autor);

        autor.setLivros(livros);

        autor.getLivros().forEach(System.out::println);
    }
}
