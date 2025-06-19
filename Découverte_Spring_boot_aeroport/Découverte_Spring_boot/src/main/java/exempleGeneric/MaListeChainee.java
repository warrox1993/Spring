package exempleGeneric;


public class MaListeChainee<TypeDeLaValeurDuNoeud> {

    static class Noeud<TypeDeLaValeurDuNoeud> {
        TypeDeLaValeurDuNoeud value;
        Noeud<TypeDeLaValeurDuNoeud> suivant;

        public Noeud(TypeDeLaValeurDuNoeud value) {
            this.value = value;
            this.suivant = null;
        }
    }

    //La tête de la liste
    private Noeud<TypeDeLaValeurDuNoeud> tete;

    public void ajouter(TypeDeLaValeurDuNoeud value) {
        Noeud<TypeDeLaValeurDuNoeud> nouveau = new Noeud<>(value);

        // suis-je entrain d'ajouter la 1 ère valeur de la liste
        if (tete == null) {
            tete = nouveau;
        } else { // tiens on va se balader jusqu'à trouver l'endroit ou suivant égal null
            Noeud<TypeDeLaValeurDuNoeud> courant = tete;

            while (courant.suivant != null) {
                courant = courant.suivant;
            }

            courant.suivant = nouveau;
        }
    }

    public void afficher() {
        Noeud<TypeDeLaValeurDuNoeud> courant = tete;
        while (courant != null) {
            System.out.print(courant.value + " -> ");
            courant = courant.suivant;
        }
        System.out.println("Je suis sorti car courant.value = null");
    }

}
