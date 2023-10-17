package org.example.Hotel;

import org.example.Animais.Animal;

import java.util.Date;

public class CheckOut extends Animal {

    Date dataCheckOut = new Date();
    Date dataCheckIn;

    public CheckOut(String especie, String nome, int idade, int andar, String tutor, Date dataCheckOut, Date dataCheckIn) {
        super(especie, nome, idade, andar, tutor);
        this.dataCheckOut = dataCheckOut;
        this.dataCheckIn = dataCheckIn;
    }

    public Date getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(Date dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }
}
