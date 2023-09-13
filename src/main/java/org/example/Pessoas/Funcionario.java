package org.example.Pessoas;

import java.util.ArrayList;
import java.util.Scanner;

public class Funcionario extends Pessoa {

    Scanner scanner = new Scanner(System.in);
    public Funcionario(String nome, int documento, int tipo){
        super(nome, documento, tipo);
    }

    public Funcionario() {
    }

}
