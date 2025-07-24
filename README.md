# 📦 Spring Boot CRUD API – Person Manager

Un projet CRUD simple réalisé avec **Java Spring Boot**. L'API permet de gérer des entités `Person` avec les opérations de base : création, lecture, mise à jour et suppression.

---

## 🚀 Fonctionnalités

- 🔍 Lister toutes les personnes
- 🧍 Récupérer une personne par ID
- ➕ Créer une nouvelle personne
- ✏️ Mettre à jour une personne existante
- ❌ Supprimer une personne
- ✅ Validation des champs avec gestion des erreurs
- ⚙️ Structure de réponse personnalisée (`status`, `message`, `data`)
- 🔐 Sécurité désactivée pour faciliter le test en développement
- 📎 Point de base `/api/v1` défini globalement via `context-path`

---

## 📁 Structure du projet
```
src/main/java/cd/vodacom/springbootcrud/
│
├── controller/ # Contrôleurs REST
├── entity/ # Modèle de données Person
├── repository/ # Interface JPA pour Person
├── service/ # Couche métier
├── util/ # ApiResponse, helpers
├── exception/ # Gestion globale des exceptions
└── config/ # Configurations Spring Security
```

## 📄 Exemple de réponse JSON
### ✅ Succès

```json
{
  "status": 200,
  "statusText": "success",
  "message": "List of people successfully recovered",
  "data": [
    {
      "id": "2f99b070-4ae6-4e59-a646-49d3f4fc89d3",
      "name": "Jean Dupont",
      "email": "jean@example.com",
      "phoneNumber": "243900000000",
      "company": "Tech Corp",
      "city": "Kinshasa",
      "country": "RDC",
      "status": "Active"
    }
  ]
}
```


## ❌ Erreur de validation
```json
{
  "status": 400,
  "statusText": "error",
  "message": "Bad Request",
  "data": {
    "email": "must be a well-formed email address",
    "name": "must not be blank"
  }
}
```

## 📦 Dépendances principales

* Spring Boot Starter Web 
* Spring Boot Starter Data JPA 
* PostgreSQL
* Spring Boot Starter Validation 
* Lombok

## ⚙️ Configuration
Dans application.properties ou application.yml :
```properties
spring.application.name=spring-boot-crud

server.servlet.context-path=/api/v1

# Connexion PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/springboot
spring.datasource.username=postgres
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## ▶️ Lancer le projet
```bash
./mvnw spring-boot:run
```
L’API sera disponible sur : http://localhost:8080/api/v1/persons

## 🔧 Tester avec Postman ou ApiDog
POST /api/v1/persons
```json
{
  "name": "Darelle Bahati Amuri",
  "email": "darelle.bahati@example.com",
  "phoneNumber": "243819999999",
  "company": "Anywr Africa",
  "city": "Kindu",
  "country": "RDC",
  "status": "Active"
} 
```

## 🧑‍💻 Auteur
This template was created by [Didierson Amuri/Anywr Africa](https://github.com/didiamuri).

## 📜 Licence
## Licence
This project is licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0).