package com.autoform.commandes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "\"USER\"")  // Spécifier le nom avec guillemets
public class User {

    // Getters et setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Constructeurs
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}