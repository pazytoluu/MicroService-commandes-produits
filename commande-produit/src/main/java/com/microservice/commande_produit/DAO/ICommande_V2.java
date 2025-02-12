package com.microservice.commande_produit.DAO;

import com.microservice.commande_produit.entities.Commande_V2;
import com.microservice.commande_produit.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ICommande_V2 extends JpaRepository<Commande_V2, Long> {
    // getting all commande by montant
    List<Commande_V2> findAllByMontant(Double montant);
    // getting all commande by date commande
    List<Commande_V2> findAllByDateCommande(LocalDateTime dateCommande);
    // getting all the products in a commande
    @Query("select c_V2.produit from Commande_V2 c_V2 where c_V2.id=: id")
    List<Produit> getAllProduit(Long id);
    // deleting all commandes on a date
    void deleteAllByDateCommande(LocalDateTime dateCommande);
    // deleting a product from a commande
    @Modifying
    @Query("delete from Commande_V2 c_V2 where c_V2.produit.id=: id")
    void deleteProductFromCommande(Long id);
}
