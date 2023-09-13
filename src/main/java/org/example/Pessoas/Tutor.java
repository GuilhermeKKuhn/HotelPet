package org.example.Pessoas;

import org.example.Animais.Animal;

import java.util.ArrayList;
import java.util.Scanner;

public class Tutor extends Pessoa {

    Scanner scanner = new Scanner(System.in);
    private ArrayList<Animal> pets;


    public Tutor(String nome, int documento, int tipo) {
        super(nome, documento, tipo);
        this.pets = new ArrayList<>();
    }

    public Tutor() {
    }

    public ArrayList<Animal> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Animal> pets) {
        this.pets = pets;
    }



}
