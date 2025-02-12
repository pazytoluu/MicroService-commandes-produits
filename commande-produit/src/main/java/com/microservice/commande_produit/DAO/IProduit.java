package com.microservice.commande_produit.DAO;

import com.microservice.commande_produit.entities.Commande_V2;
import com.microservice.commande_produit.entities.Produit;
import com.microservice.commande_produit.enumeration.Catégorie;
import com.microservice.commande_produit.enumeration.Disponibilité;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProduit extends JpaRepository<Produit, Long> {
    /*
    * the READ méthodes
    * */
    // getting all product by name
    List<Produit> findAllByNomContaining(String nom);
    // getting all product by catégorie
    List<Produit> findAllByCatégorieContaining(Catégorie categorie);
    // getting all product disponible en stock
    List<Produit> findAllByDisponibilité(Disponibilité disponibilité);
    // getting all product by price
    List<Produit> findAllByPrix(Double prix);
    // getting all product by quantité
    List<Produit> findAllByQuantité(int quantité);
    // getting all the commande where the product was in those commandes
    @Query("select cV2 from Commande_V2 cV2 where cV2.produit.id=: id")
    List<Commande_V2> findAllByProduit(Long id);
    /*
    * the DELETE méthodes
    * */
    // deleting by catégorie
    void deleteAllByCatégorie(Catégorie cat);
    // deleting by disponibilité
    void deleteAllByDisponibilité(Disponibilité disponibilité);
    // deleting by quantité
    void deleteAllByQuantité(int q);
}
