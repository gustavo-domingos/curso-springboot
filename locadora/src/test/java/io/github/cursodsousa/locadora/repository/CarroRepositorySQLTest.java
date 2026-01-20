package io.github.cursodsousa.locadora.repository;

import io.github.cursodsousa.locadora.entity.CarroEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import io.github.cursodsousa.locadora.model.Carro;
import org.hibernate.annotations.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles
public class CarroRepositorySQLTest {

    @Autowired
    CarroRepository repository;

    CarroEntity carro;

    @BeforeEach
    void setUp(){
        carro = new CarroEntity("Honda Civic", 200.0, 2023);
    }

    @Test
    @Sql("/sql/popular-carros.sql")
    void deveBuscarCarroPorModelo(){
        List<CarroEntity> lista = repository.findByModelo("SUV");

        var carro = lista.stream().findFirst().get();

        assertEquals(1, lista.size());

        assertThat(carro.getValorDiaria()).isEqualTo(80);
        assertThat(carro.getModelo()).isEqualTo("SUV");
    }

    @Test
    void deveBuscarCarroPorId(){
        var carroSalvo = repository.save(carro);

        Optional<CarroEntity> carroEncontrado = repository.findById(carroSalvo.getId());

        assertThat(carroEncontrado).isPresent();
        assertThat(carroEncontrado.get().getModelo()).isEqualTo("Honda Civic");
    }

    @Test
    void deveAtualizarCarroPorId(){
        var carroSalvo = repository.save(carro);

        carroSalvo.setAno(2028);

        var carroAtualizado = repository.save(carroSalvo);

        assertThat(carroAtualizado.getAno()).isEqualTo(2028);
    }

    @Test
    void deveDeletarCarro(){
        var carroSalvo = repository.save(carro);

        repository.deleteById(carroSalvo.getId());

        Optional<CarroEntity> carroEncontrado = repository.findById(carroSalvo.getId());

        assertThat(carroEncontrado).isEmpty();
    }
}
