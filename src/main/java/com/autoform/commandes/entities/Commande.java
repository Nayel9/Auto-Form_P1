package com.autoform.commandes.entities;

// Ces annotations permettent de transformer la classe en "entité" persistable dans la base
import jakarta.persistence.*;
import lombok.Data; // Lombok va générer automatiquement les getters/setters et constructeurs

@Entity // Indique à Spring/JPA que cette classe doit être mappée à une table en base
@Data // Lombok : évite le code inutile (getters/setters/equals/hashCode/toString)
public class Commande {

    @Id // Indique la clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentée
    private Long id;

    private String titre; // Exemple : "Commande PC portable"
    private String description; // Exemple : "Pour le service RH"
    private String statut; // Exemple : "EN_COURS", "VALIDEE", "LIVREE"
}
