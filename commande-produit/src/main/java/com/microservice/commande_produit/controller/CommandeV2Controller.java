package com.microservice.commande_produit.controller;

import com.microservice.commande_produit.entities.Commande_V2;
import com.microservice.commande_produit.entities.Produit;
import com.microservice.commande_produit.service.CommandeV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/commandesV2")
public class CommandeV2Controller {

    private final CommandeV2Service commandeService;

    @Autowired
    public CommandeV2Controller(CommandeV2Service commandeService) {
        this.commandeService = commandeService;
    }

    // Create a new commande
    @PostMapping
    public ResponseEntity<Commande_V2> createCommande(@RequestBody Commande_V2 commande) {
        Commande_V2 savedCommande = commandeService.saveCommande(commande);
        return ResponseEntity.ok(savedCommande);
    }

    // Get all commandes
    @GetMapping
    public ResponseEntity<List<Commande_V2>> getAllCommandes() {
        List<Commande_V2> commandes = commandeService.getAllCommande();
        return ResponseEntity.ok(commandes);
    }

    // Get commande by ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande_V2> getCommandeById(@PathVariable Long id) {
        Commande_V2 commande = commandeService.getCommandeById(id);
        return ResponseEntity.ok(commande);
    }

    // Get commandes by montant
    @GetMapping("/search/by-montant")
    public ResponseEntity<List<Commande_V2>> getCommandesByMontant(@RequestParam Double montant) {
        List<Commande_V2> commandes = commandeService.getAllCommandeByMontant(montant);
        return ResponseEntity.ok(commandes);
    }

    // Get commandes by dateCommande
    @GetMapping("/search/by-date")
    public ResponseEntity<List<Commande_V2>> getCommandesByDateCommande(@RequestParam LocalDateTime dateCommande) {
        List<Commande_V2> commandes = commandeService.getAllCommandeByDateCommande(dateCommande);
        return ResponseEntity.ok(commandes);
    }

    // Get all produits in a commande
    @GetMapping("/{id}/produits")
    public ResponseEntity<List<Produit>> getProduitsByCommandeId(@PathVariable Long id) {
        Commande_V2 commande = commandeService.getCommandeById(id);
        List<Produit> produits = commandeService.getAllProduit(commande);
        return ResponseEntity.ok(produits);
    }

    // Delete a commande by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommandeById(@PathVariable Long id) {
        commandeService.deleteCommandeById(id);
        return ResponseEntity.noContent().build();
    }

    // Delete commandes by dateCommande
    @DeleteMapping("/delete/by-date")
    public ResponseEntity<Void> deleteCommandesByDate(@RequestParam LocalDateTime dateCommande) {
        commandeService.deleteCommandeByDateCommande(dateCommande);
        return ResponseEntity.noContent().build();
    }

    // Delete a product from a commande
    @DeleteMapping("/{id}/produits/delete")
    public ResponseEntity<Void> deleteProductFromCommande(@PathVariable Long id) {
        commandeService.deleteProductFromCommande(id);
        return ResponseEntity.noContent().build();
    }

    // Delete all commandes
    @DeleteMapping("/delete/all")
    public ResponseEntity<Void> deleteAllCommandes() {
        commandeService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    // Update a commande
    @PutMapping
    public ResponseEntity<Commande_V2> updateCommande(@RequestBody Commande_V2 commande) {
        commandeService.updateCommande(commande);
        return ResponseEntity.ok(commande);
    }
    @PutMapping("/{commandeId}/produits/{produitId}")
    public Commande_V2 addProduitToCommande(@PathVariable Long commandeId, @PathVariable Long produitId) {
        return commandeService.addProduitToCommande(commandeId, produitId);
    }
}