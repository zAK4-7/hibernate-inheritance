package com.example.model.tableperclass;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Produit {

    // AUTO (not IDENTITY): IDENTITY unsupported w/ TABLE_PER_CLASS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    // Default ctor required by JPA
    public Produit() {}

    public Produit(String nom) {
        this.nom = nom;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}
