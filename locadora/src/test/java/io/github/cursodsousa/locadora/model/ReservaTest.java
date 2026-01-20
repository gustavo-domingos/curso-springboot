package io.github.cursodsousa.locadora.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import io.github.cursodsousa.locadora.model.exception.ReservaInvalidaException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservaTest {

    Carro carro;
    Client client;

    @BeforeEach
    void setUp(){
        carro = new Carro("couple", 200.0);
        client = new Client("João");
    }

    @Test
    void deveCriarReservaTeste(){
        //1. Cenário
        var dias = 10;

        //2. Execução
        Reserva reserva1 = new Reserva(client, carro, dias);

        //3. Verificação
        assertNotNull(reserva1);
    }

    @Test
    void deveCriarReservaComDiasNegativos(){
        // JUnit
        assertThrows(ReservaInvalidaException.class, () -> new Reserva(client, carro, -1));

        //AssertJ
        var erro = Assertions.catchThrowable(() -> new Reserva(client, carro, 0));

        Assertions.assertThat(erro)
                .isInstanceOf(ReservaInvalidaException.class)
                .hasMessage("A reserva não pode ter uma quantidade de dias menor que 1");
    }

    @Test
    void calculaTotalReservaTeste() {
        //1. Cenário
        Reserva reserva1 = new Reserva(client, carro, 10);

        //2. Execução
        double total = reserva1.calculaTotalReserva();

        //3. Verificação
        assertEquals(1950, total);
    }
}
