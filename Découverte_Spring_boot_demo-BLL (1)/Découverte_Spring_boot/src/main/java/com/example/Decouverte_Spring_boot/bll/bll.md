## bll (Business logic layer)

Couche contenant nos services (interfaces) (logique métier) et leur implémentation.

C'est à cet endroit que je vais gérer ma logique métier.

    1. Reçois une requête à traiter depuis la couche api.
    2. Fais appelle à la couche de la DAL si besoin pour récupérer des données DB nécessaire au traitement.
    3. Renvoie le tout à l'api pour que la couche puisse envoyer la réponse au front-end.