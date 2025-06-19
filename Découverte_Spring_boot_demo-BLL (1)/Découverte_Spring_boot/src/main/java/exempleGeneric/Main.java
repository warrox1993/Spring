package exempleGeneric;

public class Main {
    public static void main(String[] args) {
        var list = new MaListeChainee<String>();

        list.ajouter("Bonjour");
        list.ajouter("Salut");
        list.ajouter("Coucou");

        list.afficher();

        var listInt = new MaListeChainee<Integer>();
        listInt.ajouter(3);
        listInt.ajouter(3);
        listInt.ajouter(3);
        listInt.ajouter(3);

        listInt.afficher();
    }
}
