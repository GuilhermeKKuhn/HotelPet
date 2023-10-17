package org.example.Hotel;

import org.example.Animais.Animal;

import java.util.Date;

public class CheckIn extends Animal {
    private Date dataCheckIn = new Date();

    public CheckIn(String especie, String nome, int idade, int andar, String tutor, Date dataCheckIn) {
        super(especie, nome, idade, andar, tutor);
        this.dataCheckIn = dataCheckIn;
    }

    public Date getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(Date dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }
}
