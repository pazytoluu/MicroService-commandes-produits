package com.microservice.commande.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Component
public class ApplicationHealthIndicator implements HealthIndicator {
    /*
     * pour garantir la visibilité des modifications apportées à notre état de santé de notre application
     * pour cela nous avons utilisé le mot-clé volatile
     * */
    private volatile Health currentHealth = Health.up().build();

    @Autowired(required = true)
    private DataSource dataSource;
    /*
     * pour afficher l'état current de notre application
     * */
    @Override
    public Health health(){
        return currentHealth;
    }
    /*
     * cette méthode est execute chaque 5 seconde pour nous donner l'état de notre application
     * */
    @Scheduled(fixedRate =5000)
    public void performHealthCheck() {
        boolean isHealthy = checkCommandeTable();
        if (isHealthy) {
            currentHealth= Health.up().withDetail("message", "Commandes table is accessible").build();
        } else {
            currentHealth= Health.down().withDetail("message", "Failed to access commandes table").build();
        }
    }
    /*
     * cette méthode permet de nous donnée une vision sur la table commande s'elle contient des commandes ou non
     * */
    private boolean checkCommandeTable() {
        String query = "SELECT 1 FROM commande_V1 LIMIT 1"; // Efficient query to check existence
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            return rs.next(); // If the query returns a result, the table is accessible
        } catch (SQLException e) {
            return false; // If an exception occurs, return false (down)
        }
    }
}
