package org.example.Controler;

import org.example.Animais.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListAnimais {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Animal> animais = new ArrayList<>();

    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(ArrayList<Animal> animais) {
        this.animais = animais;
    }

    public void Listar(String tipo){
        for(int i = 0; i < animais.size(); i++){
           if(animais.get(i).getEspecie().equals(tipo)){
               System.out.println(i + ". " + animais.get(i).getNome());
           } else if (animais.get(i).getEspecie().equals(tipo)) {
               System.out.println(i + ". " + animais.get(i).getNome());
           } else if (animais.get(i).getEspecie().equals(tipo)) {
               System.out.println(i + ". " + animais.get(i).getNome());
           }
        }
    }

    public Animal seleciona() {
        System.out.print("Selecione o número do Animal que deseja: ");
        int escolha = scanner.nextInt();

        if (escolha >= 0 && escolha < animais.size()) {
            return animais.get(escolha);
        } else {
            System.out.println("Opção inválida. Por favor, selecione um número válido.");
        }
        return null;
    }
}
