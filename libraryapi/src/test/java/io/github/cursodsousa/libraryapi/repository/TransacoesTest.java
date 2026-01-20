package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService service;

    @Test
    void transacaoSimples(){
        service.executar();
    }

    @Test
    void transacaoEstadoManaged(){
        service.atualizacaoSemAtualizar();
    }
}
