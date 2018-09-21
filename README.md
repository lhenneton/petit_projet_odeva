## TP4 - Book library


### Structure

  * `main/java/car/tp4/entity`
    
    Contains all entities (EJB) (`Book` entity, `BookBean` bean examples).
    
  * `main/java/car/tp4/servlet`
  
    Contains all servlets (`BookServlet` example).
    
  * `resources/META-INF`
    
    Contains all the configuration files for the deployment.
    `persistence.xml` declares how to persist the app beans.
    We have to write a `persistence-unit` for each entity of the application.
    
  * `webapp/jsp`
  
  Contains all the jsp files, excepts the index.
  
  * `webapp/WEB-INF`
  
  Contains all the configuration files for the web application.

### How to?

To build the application and to start the server:
```
mvn clean package tomee:run
```

Once started, the application is now reachable at:
```
http://localhost:8080/books
```

To clean all data and remove the application, use `mvn:clean`.
 

### Fonctionnalités

On peut : 

* Ajouter un livre (titre, auteur, année de publication, stock) avec une demande de confirmation
* Supprimer un livre parmis la liste de livres
* Afficher les livres disponibles ou non et leur détail
* Filtrer la recherche par année, titre, auteur
* Selectionner tous les livres disponibles ou non d'un auteur
* Faire une recherche pas sous-chaine du titre
* Ajouter un ou plusieurs livres dans le panier (sans dépasser le stock)
* Supprimer un livre du panier
* Visualiser le panier
* Vider le panier
* Valider le panier qui crée une commande, fait un nouveau panier vide et met à jour les stock de chaque livre commandé
* Voir toutes les commandes
