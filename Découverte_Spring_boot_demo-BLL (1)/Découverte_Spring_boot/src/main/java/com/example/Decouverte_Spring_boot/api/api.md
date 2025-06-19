## API ou pl (presentation layer)

Couche contenant les controllers.

Ceux-ci sont assigné à un endpoint : (exemple : http://localhost:4200/auth/login) et sont
chargés de :

    1. Vérifier que vous avez bien le droit d'empreinter le chemin.
    2. S'occuper de recevoir les requêtes venant du front et de renvoyer les réponses traitées.