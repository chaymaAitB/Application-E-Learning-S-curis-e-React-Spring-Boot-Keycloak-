# Application E-Learning Sécurisée (React / Spring Boot / Keycloak)

## 1. Présentation du projet

Cette application e-learning est une application web sécurisée permettant :

* l’authentification et l’autorisation via **Keycloak**
* l’accès aux cours pour les utilisateurs authentifiés
* la gestion des cours réservée aux **administrateurs**

L’architecture repose sur :

* **React** : Frontend
* **Spring Boot** : Backend (API REST)
* **Keycloak** : Serveur d’authentification OAuth2 / OpenID Connect

---

## 2. Schéma d’architecture

<img width="1024" height="559" alt="image" src="https://github.com/user-attachments/assets/5592d3d4-8869-479c-bc3c-19e030da2669" />

---

## 3. Fonctionnalités principales

* Authentification sécurisée avec Keycloak
* Gestion des rôles :

  * `ADMIN`
  * `STUDENT`
* Accès contrôlé aux API :

  * Lecture des cours : utilisateurs connectés
  * Ajout de cours : administrateurs uniquement
* Affichage des rôles et du profil utilisateur dans React

---

## 4. Sécurité

* OAuth2 / OpenID Connect
* JWT signé et vérifié côté Spring Boot
* Protection par :

  * URL (`requestMatchers`)
  * Méthodes (`@PreAuthorize`)
* Gestion CORS et requêtes `OPTIONS`

---

## 5. Captures d’écran (à inclure dans le rapport)

1. Connexion réussie via Keycloak
   
  ![](imgs/img1.png)
  
  <img src="imgs/img1.png" alt="Screenshot" style="border: 1px solid #000000; padding: 5px;">

  ![](imgs/img2.png)

2. Informations du profil utilisateur
   
   ![](imgs/img3.png)
   
3. Rôles affichés dans l’interface React

   ![](imgs/img3.png)

   ![](imgs/img5.png)
   
5. Accès autorisé (ADMIN) aux endpoints protégés
   ![](imgs/img4.png)
   ![](imgs/img5.png)
   ![](imgs/img6.png)
   ![](imgs/img7.png)
   ![](imgs/img8.png)
   
---

## 6. Conclusion

Cette application démontre l’intégration complète d’un système d’authentification centralisé avec Keycloak et la mise en œuvre d’un contrôle d’accès robuste entre le frontend et le backend.

---



