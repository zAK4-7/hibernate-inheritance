package com.example.model.tableperclass;

import javax.persistence.Entity;

@Entity
public class Electronique extends Produit {

    private int garantie; // warranty duration (months)

    // Default ctor required by JPA
    public Electronique() {}

    public Electronique(String nom, int garantie) {
        super(nom);
        this.garantie = garantie;
    }

    // getters & setters
    public int getGarantie() { return garantie; }
    public void setGarantie(int garantie) { this.garantie = garantie; }
}
