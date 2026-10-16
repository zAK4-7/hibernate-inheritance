package com.example.model.joined;

import javax.persistence.Entity;

@Entity
public class Manager extends Employe {

    private int nombreEquipes;

    // Default ctor required by JPA
    public Manager() {}

    public Manager(String nom, int nombreEquipes) {
        super(nom);
        this.nombreEquipes = nombreEquipes;
    }

    // getters & setters
    public int getNombreEquipes() { return nombreEquipes; }
    public void setNombreEquipes(int nombreEquipes) { this.nombreEquipes = nombreEquipes; }
}
