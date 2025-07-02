package com.autoform.commandes.repositories;

import com.autoform.commandes.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

// L’interface n’a même pas besoin de code : tout est déjà géré par JpaRepository
public interface CommandeRepository extends JpaRepository<Commande, Long> {}
