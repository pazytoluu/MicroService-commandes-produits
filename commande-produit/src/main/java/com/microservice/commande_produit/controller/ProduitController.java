package com.microservice.commande_produit.controller;

import com.microservice.commande_produit.entities.Commande_V2;
import com.microservice.commande_produit.entities.Produit;
import com.microservice.commande_produit.enumeration.Catégorie;
import com.microservice.commande_produit.enumeration.Disponibilité;
import com.microservice.commande_produit.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    private final ProduitService produitService;

    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        Produit savedProduit = produitService.saveProduit(produit);
        return ResponseEntity.ok(savedProduit);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Produit produit = produitService.getProduitById(id);
        return ResponseEntity.ok(produit);
    }

    // Get products by name
    @GetMapping("/search/by-name")
    public ResponseEntity<List<Produit>> getProductsByName(@RequestParam String name) {
        List<Produit> produits = produitService.getProductsByName(name);
        return ResponseEntity.ok(produits);
    }

    // Get products by category
    @GetMapping("/search/by-category")
    public ResponseEntity<List<Produit>> getProductsByCategory(@RequestParam Catégorie category) {
        List<Produit> produits = produitService.getProductsByCategory(category);
        return ResponseEntity.ok(produits);
    }

    // Get products available in stock
    @GetMapping("/search/by-availability")
    public ResponseEntity<List<Produit>> getAvailableProducts(@RequestParam Disponibilité disponibilité) {
        List<Produit> produits = produitService.getAvailableProducts(disponibilité);
        return ResponseEntity.ok(produits);
    }

    // Get products by price
    @GetMapping("/search/by-price")
    public ResponseEntity<List<Produit>> getProductsByPrice(@RequestParam Double price) {
        List<Produit> produits = produitService.getProductsByPrice(price);
        return ResponseEntity.ok(produits);
    }

    // Get products by quantity
    @GetMapping("/search/by-quantity")
    public ResponseEntity<List<Produit>> getProductsByQuantity(@RequestParam int quantity) {
        List<Produit> produits = produitService.getProductsByQuantity(quantity);
        return ResponseEntity.ok(produits);
    }

    // Get commandes by product ID
    @GetMapping("/{id}/commandes")
    public ResponseEntity<List<Commande_V2>> getCommandesByProductId(@PathVariable Long id) {
        List<Commande_V2> commandes = produitService.getCommandesByProductId(id);
        return ResponseEntity.ok(commandes);
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit updatedProduit) {
        Produit produit = produitService.updateProduct(id, updatedProduit);
        return ResponseEntity.ok(produit);
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduitById(@PathVariable Long id) {
        produitService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    // Delete products by category
    @DeleteMapping("/delete/by-category")
    public ResponseEntity<Void> deleteProduitsByCategory(@RequestParam Catégorie category) {
        produitService.deleteProductsByCategory(category);
        return ResponseEntity.noContent().build();
    }

    // Delete products by availability
    @DeleteMapping("/delete/by-availability")
    public ResponseEntity<Void> deleteProduitsByAvailability(@RequestParam Disponibilité disponibilité) {
        produitService.deleteProductsByAvailability(disponibilité);
        return ResponseEntity.noContent().build();
    }

    // Delete products by quantity
    @DeleteMapping("/delete/by-quantity")
    public ResponseEntity<Void> deleteProduitsByQuantity(@RequestParam int quantity) {
        produitService.deleteProductsByQuantity(quantity);
        return ResponseEntity.noContent().build();
    }
}