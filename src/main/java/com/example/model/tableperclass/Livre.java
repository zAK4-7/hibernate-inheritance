package com.example.model.tableperclass;

import javax.persistence.Entity;

@Entity
public class Livre extends Produit {

    private String auteur;

    // Default ctor required by JPA
    public Livre() {}

    public Livre(String nom, String auteur) {
        super(nom);
        this.auteur = auteur;
    }

    // getters & setters
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
}
