package com.example;

import com.example.model.joined.Developpeur;
import com.example.model.joined.Employe;
import com.example.model.joined.Manager;
import com.example.model.singletable.Moto;
import com.example.model.singletable.Vehicule;
import com.example.model.singletable.Voiture;
import com.example.model.tableperclass.Electronique;
import com.example.model.tableperclass.Livre;
import com.example.model.tableperclass.Produit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-inheritance");
        EntityManager em = emf.createEntityManager();

        // ---------- CREATE ----------
        em.getTransaction().begin();

        // SINGLE_TABLE
        Voiture voiture = new Voiture("Renault", 5);
        Moto moto = new Moto("Yamaha", false);
        em.persist(voiture);
        em.persist(moto);

        // JOINED
        Developpeur dev = new Developpeur("Sara", "Python");
        Manager manager = new Manager("Youssef", 2);
        em.persist(dev);
        em.persist(manager);

        // TABLE_PER_CLASS
        Livre livre = new Livre("Design Patterns", "Erich Gamma");
        Electronique electronique = new Electronique("Laptop", 12);
        em.persist(livre);
        em.persist(electronique);

        em.getTransaction().commit();

        // ---------- READ (polymorphic queries) ----------
        System.out.println("--- Liste des véhicules ---");
        printVehicules(em);

        System.out.println("--- Liste des employés ---");
        em.createQuery("SELECT e FROM Employe e", Employe.class)
                .getResultList()
                .forEach(e -> System.out.println(e.getClass().getSimpleName() + ": " + e.getNom()));

        System.out.println("--- Liste des produits ---");
        em.createQuery("SELECT p FROM Produit p", Produit.class)
                .getResultList()
                .forEach(p -> System.out.println(p.getClass().getSimpleName() + ": " + p.getNom()));

        // ---------- UPDATE ----------
        em.getTransaction().begin();
        voiture.setNombrePortes(3);   // managed entity -> dirty checking
        dev.setLangage("Kotlin");
        livre.setAuteur("GoF");
        em.getTransaction().commit();

        // ---------- DELETE ----------
        em.getTransaction().begin();
        em.remove(moto);
        em.remove(manager);
        em.remove(electronique);
        em.getTransaction().commit();

        System.out.println("--- Véhicules après suppression ---");
        printVehicules(em);

        em.close();
        emf.close();
    }

    // Helper: print all vehicles w/ real runtime type
    private static void printVehicules(EntityManager em) {
        em.createQuery("SELECT v FROM Vehicule v", Vehicule.class)
                .getResultList()
                .forEach(v -> System.out.println(v.getClass().getSimpleName() + ": " + v.getMarque()));
    }
}
