package io.github.cursodsousa.locadora.model;

import io.github.cursodsousa.locadora.model.exception.ReservaInvalidaException;

public class Reserva {
    private Client client;
    private Carro carro;
    private int quantidadeDeDias;

    public Reserva(Client client, Carro carro, int quantidadeDeDias) {
        if(quantidadeDeDias <1){
            throw new ReservaInvalidaException("A reserva não pode ter uma quantidade de dias menor que 1");
        }
        this.client = client;
        this.carro = carro;
        this.quantidadeDeDias = quantidadeDeDias;
    }

    public double calculaTotalReserva(){
        return this.carro.calcularValorAluguel(quantidadeDeDias);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public int getQuantidadeDeDias() {
        return quantidadeDeDias;
    }

    public void setQuantidadeDeDias(int quantidadeDeDias) {
        this.quantidadeDeDias = quantidadeDeDias;
    }
}
