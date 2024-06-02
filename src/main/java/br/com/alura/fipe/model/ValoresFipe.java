package br.com.alura.fipe.model;

public class ValoresFipe {
    private Double valor;
    private String marca;
    private String modelo;
    private Integer ano;
    private String compustivel;

    public ValoresFipe(Double valor, String marca, String modelo, Integer ano, String compustivel) {
        this.valor = valor;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.compustivel = compustivel;
    }

    public Double getValor() {
        return valor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getCompustivel() {
        return compustivel;
    }

    @Override
    public String toString() {
        return "Veículo[" +
                "valor=" + valor +
                ", marca='" + marca +
                ", modelo='" + modelo +
                ", ano=" + ano +
                ", compustivel='" + compustivel +
                ']';
    }
}
