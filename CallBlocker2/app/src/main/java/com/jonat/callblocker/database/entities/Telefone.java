package com.jonat.callblocker.database.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Telefone {

    public Telefone(){

    }

    @Id @GeneratedValue
    private int id;

    @Column(name="Numero", nullable=false)
    private String numero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
