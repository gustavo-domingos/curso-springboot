package io.github.cursodsousa.locadora.model;

import static org.junit.jupiter.api.Assertions.*;
import static  org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void devecriarClientComNome(){
        //1. cenario
        var client = new Client("Gustavo");

        //2. execução
        String nome = client.getNome();

        //3. Verificação
        assertNotNull(nome);
        assertThat(nome).isEqualTo("Gustavo");
        assertThat(nome).isLessThan("Gustavo5");
        assertTrue(nome.startsWith("G"));
        assertFalse(nome.length() == 100);

        assertThat(nome.length()).isLessThan(100);
        assertThat(nome).contains("Gu");
    }

    @Test
    void deveCriarClientSemNome(){
        //1. cenario
        var client = new Client(null);

        //2. execução
        String nome = client.getNome();

        //3. Verificação
        assertNull(nome);
    }
}
