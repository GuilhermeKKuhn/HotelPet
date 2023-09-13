package org.example.Animais;

import org.example.Pessoas.Pessoa;
import org.example.Pessoas.Tutor;

import java.util.ArrayList;
import java.util.Scanner;

public class Cachorro extends Animal {


    Scanner scanner = new Scanner(System.in);

    public Cachorro(String nome, int idade, Pessoa tutor) {
        super("Cachorro", nome, idade, 1, tutor);

    }

    public Cachorro() {
    }


}

