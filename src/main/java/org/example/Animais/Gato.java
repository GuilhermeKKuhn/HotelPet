package org.example.Animais;

import org.example.Pessoas.Pessoa;
import org.example.Pessoas.Tutor;

import java.util.ArrayList;
import java.util.Scanner;

public class Gato extends Animal {

    Scanner scanner = new Scanner(System.in);

    public Gato(String nome, int idade, String tutor) {
        super("Gato", nome, idade, 2, tutor);
    }

    public Gato() {
    }


}
