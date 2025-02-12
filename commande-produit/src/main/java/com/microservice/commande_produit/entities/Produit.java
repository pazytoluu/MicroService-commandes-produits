package com.microservice.commande_produit.entities;

import com.microservice.commande_produit.enumeration.Catégorie;
import com.microservice.commande_produit.enumeration.Disponibilité;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString@EqualsAndHashCode
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private int quantité;
    @Enumerated(EnumType.STRING)
    private Catégorie catégorie;
    @Enumerated(EnumType.STRING)
    private Disponibilité disponibilité;
    @OneToMany(mappedBy = "produit")
    private List<Commande_V2> commandes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public Catégorie getCatégorie() {
        return catégorie;
    }

    public void setCatégorie(Catégorie catégorie) {
        this.catégorie = catégorie;
    }

    public Disponibilité getDisponibilité() {
        return disponibilité;
    }

    public void setDisponibilité(Disponibilité disponibilité) {
        this.disponibilité = disponibilité;
    }

    public List<Commande_V2> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande_V2> commandes) {
        this.commandes = commandes;
    }
}
