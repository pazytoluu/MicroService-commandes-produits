package com.microservice.commande_produit.service;

import com.microservice.commande_produit.DAO.IProduit;
import com.microservice.commande_produit.entities.Commande_V2;
import com.microservice.commande_produit.entities.Produit;
import com.microservice.commande_produit.enumeration.Catégorie;
import com.microservice.commande_produit.enumeration.Disponibilité;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    private final IProduit produitRepository;
    @Autowired
    public ProduitService(IProduit produitRepository) {
        this.produitRepository = produitRepository;
    }
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
    public Produit getProduitById(Long id) {
        return produitRepository.findById(id).get();
    }
    // Get all products by name
    public List<Produit> getProductsByName(String name) {
        return produitRepository.findAllByNomContaining(name);
    }

    // Get all products by category
    public List<Produit> getProductsByCategory(Catégorie categorie) {
        return produitRepository.findAllByCatégorieContaining(categorie);
    }

    // Get all products available in stock
    public List<Produit> getAvailableProducts(Disponibilité disponibilité) {
        return produitRepository.findAllByDisponibilité(disponibilité);
    }

    // Get all products by price
    public List<Produit> getProductsByPrice(Double price) {
        return produitRepository.findAllByPrix(price);
    }

    // Get all products by quantity
    public List<Produit> getProductsByQuantity(int quantity) {
        return produitRepository.findAllByQuantité(quantity);
    }

    // Get all commandes where the product is included
    public List<Commande_V2> getCommandesByProductId(Long productId) {
        return produitRepository.findAllByProduit(productId);
    }
    // Update a product
    public Produit updateProduct(Long id, Produit updatedProduit) {
        Optional<Produit> existingProduit = produitRepository.findById(id);
        if (existingProduit.isPresent()) {
            Produit produit = existingProduit.get();
            produit.setNom(updatedProduit.getNom());
            produit.setPrix(updatedProduit.getPrix());
            produit.setCatégorie(updatedProduit.getCatégorie());
            produit.setDisponibilité(updatedProduit.getDisponibilité());
            produit.setQuantité(updatedProduit.getQuantité());
            return produitRepository.save(produit);
        } else {
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }
    // Delete a product by ID
    public void deleteProductById(Long id) {
        produitRepository.deleteById(id);
    }

    // Delete products by category
    public void deleteProductsByCategory(Catégorie category) {
        produitRepository.deleteAllByCatégorie(category);
    }

    // Delete products by availability
    public void deleteProductsByAvailability(Disponibilité disponibilité) {
        produitRepository.deleteAllByDisponibilité(disponibilité);
    }

    // Delete products by quantity
    public void deleteProductsByQuantity(int quantity) {
        produitRepository.deleteAllByQuantité(quantity);
    }
}
