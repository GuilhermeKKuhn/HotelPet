package org.example.Animais;

import org.example.Pessoas.Pessoa;
import org.example.Pessoas.Tutor;


public class Animal {
   private String especie;
   private String nome;
   private int idade;
   private int andar;
   private String tutor;

    public Animal(String especie, String nome, int idade, int andar, String tutor) {
        this.especie = especie;
        this.nome = nome;
        this.idade = idade;
        this.andar = andar;
        this.tutor = tutor;

    }

    public Animal() {

    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
}
