package io.github.gustavo_domingos.convidados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class ConvidadosController {

    @Autowired
    private ConvidadosRepository repository;

    @GetMapping
    public  List<Convidados> getConvidados(){
        return repository.findAll();
    }
}
