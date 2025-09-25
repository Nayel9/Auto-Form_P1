# 🗃️ Gestionnaire de Commandes Internes – BigMeat

Projet bootcamp été 2025  
**API Java Spring Boot (CRUD, JWT, Swagger, H2)**

---

## 🚀 Contexte

La société **BigMeat** souhaite moderniser le suivi de ses commandes de fournitures entre services.  
Ce projet met en place une API REST sécurisée pour gérer, suivre et administrer les commandes internes.

---

## 🎯 Objectifs fonctionnels

- **CRUD complet** sur les commandes internes (ajouter, afficher, modifier, supprimer)
- **Authentification JWT** pour sécuriser l’accès aux endpoints
- **Base de données H2** en mémoire (zéro config, idéale pour le dev et la démo)
- **Documentation Swagger/OpenAPI** interactive et détaillée

---

## 🛠️ Stack technique

- Java 24
- Spring Boot 3
- Spring Data JPA
- Spring Security + JWT (`jjwt`)
- Base H2 (in-memory)
- Swagger UI (springdoc-openapi)
- Maven

---

## 💻 Lancement rapide

1. **Cloner le repo :**
   ```bash
   git clone https://github.com/<ton_user>/Auto-Form_P1.git
   cd Auto-Form_P1
   
2. **Lancer l’application :**

- Depuis IntelliJ (ou ton IDE préféré), `Run` la classe principale `GestionnaireCommandesApplication`
- Ou via Maven :

   ```bash
    mvn clean spring-boot:run

3. **Accéder à l’API et aux outils :**
   - Swagger UI : [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - H2 Console : [http://localhost:8080/h2-console](http://localhost:8080/h2-console) 
     - JDBC URL : `jdbc:h2:mem:testdb` 
     - user: `sa`
     - password: laisser vide

## 🔒 Authentification JWT – mode d’emploi

1. **Login pour obtenir un token :**
   - POST `/api/auth/login`
   - Body JSON :
     ```json
     {
       "username": "admin",
       "password": "password"
     }
     ```
   - → La réponse contient un JWT.
   
2. **Utiliser le token sur toutes les routes protégées (CRUD commandes) :**
    
    - Ajouter un header :

    ``` makefile
        Authorization: Bearer <token>
    ```
   - Exemple avec Swagger : clique sur « Authorize », colle le token.

3. **Users de test disponibles dès le démarrage (voir `data.sql`) :**
   - `admin` / `password`
   - `user` / `1234`

## 📚 Endpoints principaux

| Méthode | Endpoint            | Description                | Auth requise |
| ------- | ------------------- | -------------------------- | ------------ |
| POST    | /api/auth/login     | Authentification (JWT)     | ❌            |
| GET     | /api/commandes      | Liste toutes les commandes | ✅            |
| GET     | /api/commandes/{id} | Détail d'une commande      | ✅            |
| POST    | /api/commandes      | Créer une commande         | ✅            |
| PUT     | /api/commandes/{id} | Modifier une commande      | ✅            |
| DELETE  | /api/commandes/{id} | Supprimer une commande     | ✅            |

✅ = nécessite un JWT dans le header

## 🧪 Tests

- Tests manuels réalisés via Swagger UI et Postman (CRUD et auth JWT).

- Un test unitaire d’exemple (GET commandes) est fourni dans src/test/java.

## 📂 Structure du projet

- `src/main/java/com/bigmeat/gestionnairecommandes` : code source principal
  - `controller` : contrôleurs REST
  - `model` : entités JPA
  - `repository` : interfaces de persistance
  - `service` : logique métier
  - `security` : configuration JWT et sécurité

- `/src/main/resources` : ressources de l’application
  - `application.properties` : config Spring Boot
  - `data.sql` : données de test initiales (users et commandes)
  - `schema.sql` : schéma de la base H2

## 🧑‍💻 Auteur
- **Nahuel Vainer** – [Mon GitHub](https://github.com/Nayel9)
- **LinkedIn** – [Mon LinkedIn](https://www.linkedin.com/in/nahuel-vainer-0bbb48165)
