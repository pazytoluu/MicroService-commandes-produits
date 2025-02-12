package com.microservice.commande.controller;

import com.microservice.commande.entities.Commande_V1;
import com.microservice.commande.services.Commande_V1_Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandes")
@RefreshScope
public class Commande_V1_Controller {
    /*
     * Notre service commande où on trouve les différentes méthodes CRUD qu'on a développé
     * */
    private final Commande_V1_Service commandeService;

    // Proper constructor injection instead of field injection
    public Commande_V1_Controller(Commande_V1_Service commandeService) {
        this.commandeService = commandeService;
    }
    /*
     * Notre config pour les jours des commandes où on va afficher
     * à l'aide du config server
     * */
    @Value("${mes-config-ms.commandes-last}")
    private int commandesLast;
    /*
     * FindAll méthode
     * */
    @GetMapping("/single-page")
    public String showPage() {
        return "single-page";  // return the SPA template
    }
    @GetMapping
    public ResponseEntity<List<Commande_V1>> getCommandes() {
        List<Commande_V1> commandes= commandeService.getAllCommandes();
        return ResponseEntity.ok(commandes);
    }
    /*
     * find by Id méthode
     * */
    @GetMapping("/{id}")
    public ResponseEntity<Commande_V1> getCommandeById(@PathVariable Long id) {
        Commande_V1 commande = commandeService.getCommandeById(id);
        if(commande!= null)
            return ResponseEntity.ok(commande);
        else
            return ResponseEntity.notFound().build();
    }
    /*
     * affichage des commandes des derniers jours où on spécifie le nombre de jours à l'aide de config sever
     * @Param: commandesLast
     * */
    @GetMapping("/recent")
    public ResponseEntity<List<Commande_V1>> getRecentCommandes() {
        List<Commande_V1> commandes = commandeService.getRecentCommandes(commandesLast);
        return ResponseEntity.ok(commandes);
    }
    /*
     * Ajouter une nouvelle commande
     * */
    @PostMapping
    public ResponseEntity<Commande_V1> createCommande(@RequestBody Commande_V1 commande){
        Commande_V1 createdCommande = commandeService.saveCommande(commande);
        return ResponseEntity.status(201).body(createdCommande);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteCommandeById(@PathVariable("id") Long id){
        try{
            commandeService.deleteCommandeById(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/updating/{id}")
    public ResponseEntity<Commande_V1> updateCommande(@RequestBody Commande_V1 commande, @PathVariable Long id){
        return ResponseEntity.ok(commandeService.updateCommande(commande,id));
    }
}
