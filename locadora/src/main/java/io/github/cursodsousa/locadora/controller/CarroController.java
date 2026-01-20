package io.github.cursodsousa.locadora.controller;

import io.github.cursodsousa.locadora.entity.CarroEntity;
import io.github.cursodsousa.locadora.model.exception.EntityNotFoundExceptions;
import io.github.cursodsousa.locadora.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carros")
public class CarroController {

    private final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody CarroEntity carro){

        try {
            var carroSalvo = service.salvar(carro);
            return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());

        }

    }

    @GetMapping("{id}")
    public ResponseEntity<CarroEntity> detalheCarro(@PathVariable Long id){
        try {
            var carroEncontrado = service.buscarPorId(id);
            return ResponseEntity.ok(carroEncontrado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CarroEntity>> listar(){
        return ResponseEntity.ok(service.listarTodas());
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody CarroEntity dadosAtualizado){
        try {
            service.atualizar(id, dadosAtualizado);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundExceptions e){
            return ResponseEntity.notFound().build();
        }
    }
}
