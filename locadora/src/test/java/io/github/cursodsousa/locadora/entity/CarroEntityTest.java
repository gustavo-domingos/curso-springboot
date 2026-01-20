package io.github.cursodsousa.locadora.entity;

import io.github.cursodsousa.locadora.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroEntityTest {

    @Autowired
    CarroRepository repository;

    @Test
    void deveSalvarUmCarro(){
        var entity = new CarroEntity("Sedan", 100.0, 2024);

        repository.save(entity);

        assertNotNull(entity.getId());
    }

}