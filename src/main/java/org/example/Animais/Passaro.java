package org.example.Animais;

import org.example.Pessoas.Pessoa;
import org.example.Pessoas.Tutor;

import java.util.ArrayList;
import java.util.Scanner;

public class Passaro extends Animal {

    Scanner scanner = new Scanner(System.in);

    public Passaro(){
    }

    public Passaro(String nome, int idade, Pessoa tutor) {
        super("Passaro", nome, idade, 3, tutor);
    }



}
