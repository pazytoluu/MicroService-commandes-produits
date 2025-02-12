package com.microservice.commande.DAO;

import com.microservice.commande.entities.Commande_V1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ICommande_V1 extends JpaRepository<Commande_V1, Long> {
    @Query("SELECT c FROM Commande_V1 c WHERE c.date >= :date")
    List<Commande_V1> findRecentCommandes(@Param("date") LocalDate date);
}
