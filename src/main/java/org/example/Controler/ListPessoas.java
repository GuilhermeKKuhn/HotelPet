package org.example.Controler;

import org.example.Pessoas.Pessoa;

import java.util.ArrayList;
import java.util.Scanner;

public class ListPessoas {

    Scanner scanner = new Scanner(System.in);
    private ArrayList<Pessoa> pessoas = new ArrayList<>();

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void listar(int tipo) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getTipo() == tipo) {
                System.out.println(i + ". " + pessoas.get(i).getNome());
            } else if (pessoas.get(i).getTipo() == tipo) {
                System.out.println(i + ". " + pessoas.get(i));

            }
        }
    }

    public Pessoa seleciona() {
        System.out.print("Selecione a pessoa: ");
        int escolha = scanner.nextInt();

        if (escolha >= 0 && escolha < pessoas.size()) {
            if(pessoas.get(escolha).getTipo() == 1){
                return pessoas.get(escolha);
            }
        } else {
            System.out.println("Opção inválida. Por favor, selecione um número válido.");
        }
        return null;
    }
}
