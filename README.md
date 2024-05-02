# P6 - Développez une application full-stack complète

# MDD

## Technologies utilisées

* Java 17
* Spring Boot 3
* Spring Security avec authentification JWT
* Maven
* Lombok
* MySQL
* Angular 14

## Démarrage du projet

Cloner le dépôt :

> git clone [https://github.com/Herve-D/P6_Application_Fullstack](https://github.com/Herve-D/P6_Application_Fullstack.git)

### MySQL

Le script SQL est disponible ici : `ressources/sql/script.sql`

Dans le fichier `back/src/main/resources/application.properties` modifiez avec vos informations de base de données :
```
spring.datasource.username=xxx
spring.datasource.password=xxx
```

### Installation des dépendances

Pour le back-end :

> cd back
> mvn clean install

Pour le front-end :

> cd front
> npm install

### Démarrage back-end

> mvn spring-boot:run

### Démarrage front-end

> npm run start

Le front sera accessible à l'adresse [localhost:4200](http://localhost:4200)
