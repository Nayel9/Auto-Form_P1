package com.autoform.commandes.controllers;

    import com.autoform.commandes.entities.Commande;
    import com.autoform.commandes.repositories.CommandeRepository;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.Parameter;
    import io.swagger.v3.oas.annotations.media.Content;
    import io.swagger.v3.oas.annotations.media.Schema;
    import io.swagger.v3.oas.annotations.parameters.RequestBody;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.responses.ApiResponses;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/commandes")
    public class CommandeController {

        private final CommandeRepository commandeRepository;

        public CommandeController(CommandeRepository commandeRepository) {
            this.commandeRepository = commandeRepository;
        }

        @Operation(
                summary = "Liste toutes les commandes",
                description = "Renvoie la liste complète des commandes internes enregistrées en base."
        )
        @ApiResponse(
                responseCode = "200",
                description = "Liste des commandes",
                content = @Content(mediaType = "application/json")
        )
        @GetMapping
        public List<Commande> getAllCommandes() {
            return commandeRepository.findAll();
        }

        @Operation(
                summary = "Récupère une commande par son identifiant",
                description = "Permet de récupérer les détails d'une commande à partir de son id."
        )
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "Commande trouvée"),
                @ApiResponse(responseCode = "404", description = "Commande non trouvée")
        })
        @GetMapping("/{id}")
        public ResponseEntity<Commande> getCommandeById(
                @Parameter(description = "Identifiant de la commande") @PathVariable Long id
        ) {
            return commandeRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @Operation(
                summary = "Crée une nouvelle commande",
                description = "Enregistre une nouvelle commande interne à partir des données fournies."
        )
        @ApiResponse(responseCode = "201", description = "Commande créée avec succès")
        @PostMapping
        public Commande createCommande(
                @RequestBody(
                        description = "Données de la commande à créer",
                        required = true,
                        content = @Content(schema = @Schema(implementation = Commande.class))
                )
                @org.springframework.web.bind.annotation.RequestBody Commande commande
        ) {
            return commandeRepository.save(commande);
        }

        @Operation(
                summary = "Met à jour une commande",
                description = "Modifie une commande existante selon son id."
        )
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "Commande mise à jour"),
                @ApiResponse(responseCode = "404", description = "Commande non trouvée")
        })
        @PutMapping("/{id}")
        public ResponseEntity<Commande> updateCommande(
                @Parameter(description = "Identifiant de la commande à modifier") @PathVariable Long id,
                @RequestBody(
                        description = "Nouvelles données de la commande",
                        required = true,
                        content = @Content(schema = @Schema(implementation = Commande.class))
                )
                @org.springframework.web.bind.annotation.RequestBody Commande commandeDetails
        ) {
            return commandeRepository.findById(id)
                    .map(commande -> {
                        commande.setTitre(commandeDetails.getTitre());
                        commande.setDescription(commandeDetails.getDescription());
                        commande.setStatut(commandeDetails.getStatut());
                        Commande updated = commandeRepository.save(commande);
                        return ResponseEntity.ok(updated);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }

        @Operation(
                summary = "Supprime une commande",
                description = "Efface une commande existante selon son id."
        )
        @ApiResponses({
                @ApiResponse(responseCode = "204", description = "Commande supprimée"),
                @ApiResponse(responseCode = "404", description = "Commande non trouvée")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Object> deleteCommande(
                @Parameter(description = "Identifiant de la commande à supprimer") @PathVariable Long id
        ) {
            return commandeRepository.findById(id)
                    .map(commande -> {
                        commandeRepository.delete(commande);
                        return ResponseEntity.noContent().build();
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
    }