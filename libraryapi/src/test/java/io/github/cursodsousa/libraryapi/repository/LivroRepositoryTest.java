package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("31223-3124");
        livro.setPreco(BigDecimal.valueOf(12.1));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("teste");
        livro.setDataPublicacao(LocalDate.of(1999, 3, 3));

        Autor autor = autorRepository.findById(UUID.fromString("0fdbc1bc-7e74-454e-9f14-ed650dbe3c69")).orElse(null);
        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarAutorELivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("12331-3124");
        livro.setPreco(BigDecimal.valueOf(14.1));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("abcde");
        livro.setDataPublicacao(LocalDate.of(1332, 3, 6));

        Autor autor = new Autor();
        autor.setNomeAutor("abcde");
        autor.setNacionalidade("Europeu");
        autor.setDataNascimento(LocalDate.of(1300, 5, 3));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("31223-3124");
        livro.setPreco(BigDecimal.valueOf(12.1));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("teste");
        livro.setDataPublicacao(LocalDate.of(1999, 3, 3));

        Autor autor = new Autor();
        autor.setNomeAutor("João");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1980, 3, 30));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutorLivro(){
        var livro = livroRepository.findById(UUID.fromString("2974bd5a-f0ad-427f-be2c-72834769bf62")).orElse(null);
        Autor autor = autorRepository.findById(UUID.fromString("d6fe9cd8-55e0-466b-9427-9a95341b84e4")).orElse(null);
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    public void deletar(){
        var livro = UUID.fromString("5ecaf05b-3918-4b9f-a9ad-83955daa62f4");
        livroRepository.deleteById(livro);
    }

    @Test
    public void deletarCascade(){
        var livro = UUID.fromString("2974bd5a-f0ad-427f-be2c-72834769bf62");
        livroRepository.deleteById(livro);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("bac13c94-b99b-4142-b588-1ad1facf1115");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro " + livro.getTitulo());
        System.out.println("Autor " + livro.getAutor().getNomeAutor());
    }

    @Test
    public void pesquisaPortitulo(){
        List<Livro> livros = livroRepository.findByTitulo("bbbbbbb");
        livros.forEach(System.out::println);
    }

    @Test
    public void pesquisaPorIsbn(){
        Optional<Livro> livro = livroRepository.findByIsbn("31223-3124");
        System.out.println(livro);
    }

    @Test
    public void pesquisaPorTituloEPreco(){
        var preco = BigDecimal.valueOf(14.10);

        List<Livro> livros = livroRepository.findByTituloAndPreco("bbbbbbb", preco);
        livros.forEach(System.out::println);
    }

    @Test
    public void listarLivrosComQueryJpQL(){
        var resultado = livroRepository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarAutoresDosLivros(){
        var resultado = livroRepository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarNomesDiferentesLivros(){
        var resultado = livroRepository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarGenerosAutoresBrasileiros(){
        var resultado = livroRepository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    public void findByGenero(){
        var resultado = livroRepository.findByGenero(GeneroLivro.ROMANCE, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    public void findByGeneroPositionalParameters(){
        var resultado = livroRepository.findByGeneroPositionalParameters(GeneroLivro.ROMANCE, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    public void deleteByGenero(){
        livroRepository.deleteByGenero(GeneroLivro.BIOGRAFIA);
    }

    @Test
    public void updateDataPublicacao(){
        livroRepository.updateDataPublicacao(LocalDate.of(2000, 3, 3));
    }

}