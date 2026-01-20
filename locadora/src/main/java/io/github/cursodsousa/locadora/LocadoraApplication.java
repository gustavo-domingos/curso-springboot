package io.github.cursodsousa.locadora;

import io.github.cursodsousa.locadora.model.Carro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocadoraApplication {

	public static void main(String[] args) {

			Carro carro = new Carro("Sedan", 100.0);
			double total = carro.calcularValorAluguel(3);

		SpringApplication.run(LocadoraApplication.class, args);
	}

}
