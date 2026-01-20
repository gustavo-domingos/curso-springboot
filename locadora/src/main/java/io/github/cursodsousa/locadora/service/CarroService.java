package io.github.cursodsousa.locadora.service;

import io.github.cursodsousa.locadora.entity.CarroEntity;
import io.github.cursodsousa.locadora.model.exception.EntityNotFoundExceptions;
import io.github.cursodsousa.locadora.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private  final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public CarroEntity salvar(CarroEntity carro){
        if(carro.getValorDiaria() <= 0){
           throw new IllegalArgumentException("Preço da diária não pode ser negativo");
        }
        return carroRepository.save(carro);
    }

    public CarroEntity atualizar(Long id, CarroEntity carroAtualizado){
        var carroExistente = carroRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundExceptions("Carro não encontrado"));

        carroExistente.setAno(carroAtualizado.getAno());
        carroExistente.setModelo(carroAtualizado.getModelo());
        carroExistente.setValorDiaria(carroAtualizado.getValorDiaria());

        return carroRepository.save(carroExistente);
    }

    public void deletar(Long id) {
        var carroExistente = carroRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundExceptions("Carro não encontrado"));
        carroRepository.delete(carroExistente);
    }

    public CarroEntity buscarPorId(Long id) {
        return carroRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundExceptions("Carro não encontrado"));
    }

    public List<CarroEntity> listarTodas(){
        return carroRepository.findAll();
    }
}
