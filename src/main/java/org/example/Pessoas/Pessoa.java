package org.example.Pessoas;

public class Pessoa {
    private String nome;
    private int documento;
    private int tipo;

    public Pessoa(String nome, int documento, int tipo) {
        this.nome = nome;
        this.documento = documento;
        this.tipo = tipo;
    }

    public Pessoa(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
