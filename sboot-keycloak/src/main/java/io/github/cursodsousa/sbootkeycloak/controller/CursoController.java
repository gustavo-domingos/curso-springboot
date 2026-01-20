package io.github.cursodsousa.sbootkeycloak.controller;

import io.github.cursodsousa.sbootkeycloak.model.Curso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cursos")
public class CursoController {

    @GetMapping("java")
    public ResponseEntity<Curso> cursoJava(){
        return ResponseEntity.ok(new Curso(UUID.randomUUID(), "Java"));
    }

    @GetMapping("spring-boot")
    public ResponseEntity<Curso> cursoSpringBoot(){
        return ResponseEntity.ok(new Curso(UUID.randomUUID(), "Springboot"));
    }

    @GetMapping("exclusivos")
    public ResponseEntity<List<Curso>> cursoExclusivos(){
        Curso angular = new Curso(UUID.randomUUID(), "angular");
        Curso react = new Curso(UUID.randomUUID(), "react");
        Curso view = new Curso(UUID.randomUUID(), "view");

        return ResponseEntity.ok(List.of(angular, react, view));
    }

    @GetMapping("abertos")
    public ResponseEntity<List<Curso>> cursoAbertos(){
        Curso logica = new Curso(UUID.randomUUID(), "logica");
        Curso sql = new Curso(UUID.randomUUID(), "sql");


        return ResponseEntity.ok(List.of(logica, sql));
    }
}
