package org.example.Controler;

import org.example.Animais.Animal;
import org.example.Pessoas.Pessoa;

import java.util.ArrayList;

public class ControllerHotel {

        private ArrayList<Animal> reservas = new ArrayList<>();

    public ArrayList<Animal> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Animal> reservas) {
        this.reservas = reservas;
    }
}
