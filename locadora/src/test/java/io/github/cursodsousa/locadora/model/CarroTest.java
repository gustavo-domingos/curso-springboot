package io.github.cursodsousa.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarroTest {

    @Test
    @DisplayName("Deve calcular o valor correto do aluguel")
    void deveCalcularValorAluguel(){
        //1 - cenário
        Carro carro = new Carro("Sedan", 100.0);

        //2 - Execucação
        double total = carro.calcularValorAluguel(3);

        //3 - verificação
        Assertions.assertEquals(300.0, total);
    }

    @Test
    @DisplayName("Deve calcular o valor correto do aluguel com desconto")
    void deveCalcularValorAluguelComDesconto(){
        //1 - cenário
        Carro carro = new Carro("Sedan", 100.0);
        int quantidadeDias = 6;

        //2 - Execucação
        double total = carro.calcularValorAluguel(quantidadeDias);

        //3 - verificação
        Assertions.assertEquals(550.0, total);
    }

}
