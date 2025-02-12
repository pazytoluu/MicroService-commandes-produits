package com.microservice.commande.services;

import com.microservice.commande.DAO.ICommande_V1;
import com.microservice.commande.Exceptions.ResourceNotFound;
import com.microservice.commande.entities.Commande_V1;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Commande_V1_Service {
    /*
     * Commande Repository
     * */
    private final ICommande_V1 commandeRepository;

    // Constructor injection instead of field injection
    public Commande_V1_Service(ICommande_V1 commandeRepository) {
        this.commandeRepository = commandeRepository;
    }
    /*
     * méthode pour la création et la modification des commandes
     * */
    public Commande_V1 saveCommande(Commande_V1 commande) {
        return commandeRepository.save(commande);
    }
    /*
     * méthode pour l'affichage des commandes
     * */
    public List<Commande_V1> getAllCommandes() {
        return commandeRepository.findAll();
    }
    /*
     * méthode pour l'affichage d'une commande par son ID
     * */
    public Commande_V1 getCommandeById(Long id) {
        return commandeRepository.findById(id).get();
    }
    /*
     * méthode pour la suppression d'une commande par son ID
     * */
    public void deleteCommandeById(Long id) {
        commandeRepository.deleteById(id);
    }
    /*
     * méthode pour la suppression d'une commande
     * */
    public void deleteCommande(Commande_V1 commande) {
        commandeRepository.delete(commande);
    }
    /*
     * méthode pour afficher les commandes passées dans les derniers jours
     * */
    public List<Commande_V1> getRecentCommandes(int lastDays) {
        LocalDate thresholdDate = LocalDate.now().minusDays(lastDays);
        return commandeRepository.findRecentCommandes(thresholdDate);
    }
    /*
     * méthode pour la modification d'une commande
     * */
    public Commande_V1 updateCommande(Commande_V1 commande, Long id) {
        Commande_V1 updatedCommande = commandeRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Commande not found with id: " + id));
        updatedCommande.setDescription(commande.getDescription());
        updatedCommande.setDate(commande.getDate());
        updatedCommande.setMontant(commande.getMontant());
        updatedCommande.setQuantité(commande.getQuantité());
        return commandeRepository.save(updatedCommande);
    }
}
