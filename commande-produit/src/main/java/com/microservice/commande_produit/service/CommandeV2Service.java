package com.microservice.commande_produit.service;

import com.microservice.commande_produit.DAO.ICommande_V2;
import com.microservice.commande_produit.DAO.IProduit;
import com.microservice.commande_produit.entities.Commande_V2;
import com.microservice.commande_produit.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommandeV2Service {
    private final ICommande_V2 commandeRepository;

    private final IProduit produitRepository;
    @Autowired
    public CommandeV2Service(ICommande_V2 commandeRepository, IProduit produitRepository) {
        this.commandeRepository = commandeRepository;
        this.produitRepository = produitRepository;
    }

    public Commande_V2 saveCommande(Commande_V2 commande) {
        return commandeRepository.save(commande);
    }
    public List<Commande_V2> getAllCommande(){
        return commandeRepository.findAll();
    }
    public Commande_V2 getCommandeById(Long id){
        return commandeRepository.findById(id).get();
    }
    public List<Commande_V2> getAllCommandeByMontant(Double montant){
        return commandeRepository.findAllByMontant(montant);
    }
    public List<Commande_V2> getAllCommandeByDateCommande(LocalDateTime dateCommande){
        return commandeRepository.findAllByDateCommande(dateCommande);
    }
    public List<Produit> getAllProduit(Commande_V2 commande){
        return commandeRepository.getAllProduit(commande.getId());
    }

    public void deleteCommandeById(Long id){
        commandeRepository.deleteById(id);
    }
    public void deleteCommandeByDateCommande(LocalDateTime dateCommande){
        commandeRepository.deleteAllByDateCommande(dateCommande);
    }
    public void deleteProductFromCommande(Long id){
        commandeRepository.deleteProductFromCommande(id);
    }
    public void deleteAll(){
        commandeRepository.deleteAll();
    }
    public void updateCommande(Commande_V2 commande){
        commandeRepository.save(commande);
    }
    public Commande_V2 addProduitToCommande(Long commandeId, Long produitId) {
        Commande_V2 commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande not found"));
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit not found"));

        // Ajout du produit à la commande
        commande.setProduit(produit);

        // Sauvegarde de la commande mise à jour
        return commandeRepository.save(commande);
    }
}
