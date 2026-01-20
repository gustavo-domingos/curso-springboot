package io.github.cursodsousa.libraryapi.exceptions;

import lombok.Getter;

public class CampoInvalidoExceptions extends RuntimeException{
    @Getter
    private String campo;

    public CampoInvalidoExceptions(String campo, String mensagem){
        super(mensagem);
        this.campo = campo;
    }
}
