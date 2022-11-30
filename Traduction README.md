# FREE NOW backend candidate test 

## Description de la tâche 
Vous devriez pouvoir démarrer l'exemple d'application en exécutant com.freenow.FreeNowServerApplicantTestApplication , qui démarre un serveur Web sur le port 8080 (http://localhost:8080) et sert SwaggerUI où peut inspecter et essayez les terminaux existants. 

Le projet est basé sur un petit service Web qui utilise les technologies suivantes : 

* Java 11 
* Spring Boot 
* Database H2 (In-Memory) 
* Maven 


Vous devez être conscient des conventions suivantes lorsque vous travaillez sur cet exercice : 

* All new les entités doivent avoir un ID de type Long et un date_created de type ZonedDateTime . 
* L'architecture du service Web est construite avec les composants suivants :
   * DataTransferObjects : Objets qui sont utilisés pour la communication vers l'extérieur via l'API
   * Contrôleur : implémente la logique de traitement du service Web, l'analyse des paramètres et la validation des entrées et des sorties.
   * Service : implémente la logique métier et gère l'accès aux DataAccessObjects .
   * DataAccessObjects : Interface pour la base de données. Insère, met à jour, supprime et lit des objets de la base de données.
   * DomainObjects : objets fonctionnels qui peuvent être persistants dans la base de données. 
* TestDrivenDevelopment est un bon choix, mais c'est à vous de décider comment vous testez votre code. 
* N'hésitez pas à utiliser Java ainsi que Kotlin 
* Nous fournissons un formateur de code pour IntelliJ IDEA et Eclipse dans le dossier 

etc. Vous devez vous engager dans un référentiel git local et inclure le référentiel git (. git /) dans le téléchargement. 

_ REMARQUE : Veuillez NE PAS publier le projet, par exemple en le téléchargeant sur GitHub ou similaire ! _ 

--- 


## Tâche 1 
* Écrire un nouveau contrôleur pour la maintenance des voitures (CRUD).
   * Décidez vous-même de l'apparence des méthodes.
   * Entity Car : doit avoir au moins les caractéristiques suivantes : 
        license_plate , seat_count , cabriolet, rating, engine_type (electric, gas, ...)
   * Fabricant d'entité : Décidez vous-même si vous allez utiliser une nouvelle table ou simplement une colonne de chaîne dans la table des voitures. 
* Étendez le DriverController pour permettre aux conducteurs de sélectionner une voiture avec laquelle ils conduisent. 
* Étendez le DriverController pour permettre aux conducteurs de désélectionner une voiture. 
* Étendez le DriverDo pour mapper la voiture sélectionnée au conducteur. 
* Ajouter des exemples de données à resources/ data.sql 

--- 


## Tâche 2 
Premier arrivé, premier servi : Une voiture peut être sélectionnée par exactement un Conducteur EN LIGNE. 
Si un deuxième conducteur essaie de sélectionner une voiture déjà utilisée, 
vous devez lancer une CarAlreadyInUseException . 

--- 


## Tâche 3 
Imaginez une interface de gestion des chauffeurs utilisée en interne par les employés de FREE NOW pour créer et modifier les données relatives aux chauffeurs. 
Pour une nouvelle fonctionnalité de recherche, nous avons besoin d'un point de terminaison pour rechercher des pilotes. 
Il devrait être possible de rechercher des conducteurs par leurs attributs
(nom d'utilisateur, online_status ) ainsi que par les caractéristiques de la voiture 
(plaque d'immatriculation, classification, etc ). 

* implémenter un nouveau point de terminaison pour rechercher ou étendre un existant 
* attributs conducteur/voiture comme paramètres d'entrée 
* renvoyer la liste des conducteurs 

--- 


## Tâche 4 (facultatif ) 
Cette tâche est _ volontairement _ , 
si vous ne pouvez pas en avoir assez piratage des défis technologiques, mise en œuvre de la sécurité. 
Sécurisez l'API afin que l'authentification soit nécessaire pour y accéder. Les détails dépendent de vous. 

Veuillez inclure des instructions sur l'authentification/la connexion, afin que nous puissions tester les points de terminaison que vous avez implémentés ! 

--- 

Bonne chance ! 
❤ ️ GRATUIT MAINTENANT 



_ REMARQUE : Assurez-vous de ne soumettre aucune donnée personnelle avec le résultat de vos tests. Les données personnelles sont par exemple votre nom, votre date de naissance, votre adresse e-mail, etc. _


