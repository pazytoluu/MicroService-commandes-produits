# MicroService-commandes-produits
# Microservice Project

## Introduction

Ce projet est une architecture microservices développée en Java avec Spring Boot et Spring Cloud. Il comprend plusieurs services indépendants qui communiquent entre eux via Eureka et un serveur de configuration centralisé.

## Structure du Projet

Le projet est composé des microservices suivants :

### 1. **Commande** (`commande/`)

- Gère les commandes des utilisateurs.
- Contient la configuration `bootstrap.yml` pour la connexion aux services.
- Dépend probablement du service `commande-produit` pour la gestion des produits associés aux commandes.

### 2. **Commande-Produit** (`commande-produit/`)

- Associe les commandes aux produits.
- Fait partie du processus de gestion des commandes.

### 3. **Config Server** (`config-server/`)

- Fournit une configuration centralisée pour tous les services.
- Contient un fichier `application.yml` définissant les paramètres globaux.
- Utilisé pour stocker les configurations et éviter leur duplication dans chaque microservice.

### 4. **Eureka Server** (`eureka-server/`)

- Sert de registre de service pour la découverte dynamique des microservices.
- Permet aux services de s'enregistrer et de se découvrir mutuellement sans configuration manuelle.

## Technologies utilisées

- **Spring Boot** : Framework principal pour le développement des microservices.
- **Spring Cloud** : Pour la gestion de l’infrastructure et des communications entre services.
- **Eureka** : Service de découverte pour le registre des microservices.
- **Config Server** : Pour la gestion centralisée de la configuration.
- **Java** : Langage de programmation utilisé.
- **YAML (**``**)** : Fichiers de configuration pour chaque service.

## Installation et Exécution

1. Assurez-vous d'avoir **Java 17+** et **Maven** installés.
2. Clonez ou extrayez le projet.
3. Démarrez d'abord le **Eureka Server** :
   ```sh
   cd eureka-server
   mvn spring-boot:run
   ```
4. Démarrez ensuite le **Config Server** :
   ```sh
   cd config-server
   mvn spring-boot:run
   ```
5. Enfin, démarrez les services `commande` et `commande-produit` :
   ```sh
   cd commande
   mvn spring-boot:run
   ```
   ```sh
   cd commande-produit
   mvn spring-boot:run
   ```

## Conclusion

Ce projet démontre une architecture microservices modulaire en Java avec Spring Boot et Spring Cloud. Chaque service est indépendant et scalable, facilitant le développement et la maintenance.


