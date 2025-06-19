package com.example.Decouverte_Spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO : PARLER DU DI dans l'IOC.

/**
 * @SpringBootApplication :
 * Définition : annotation (qui contient des métadonnées qu'on place sur une classe) qui regroupe
 * 3 comportements différents.
 * <br>
 * 1)@Configuration : Ceci va contenir les définitions de ce qu'on appelle un beans.
 * <br>-> Un bean : c'est une fonction créant des objets qui sont gérés par Spring.
 * <br><br>
 * 2)@EnableAutoConfiguration : Spring boot qui va automatiquement configurer de nombreux composants pour vous.
 * <br> Notamment, le serveur web embarqué : TomCat, le devTools) tout ce que vous avez globalement spécifié
 * dans vos dépendances.
 * <br><br>
 * 3)@ComponentScan : Grâce à cet annotation, je spécifie à Spring qu'il doit scanner nos package
 * (ici : com.example.Decouverte_Spring_boot) et tous ses sous-packages pour y détecter les classes
 * qu'il doit instancier dans son contexte.
 */
@SpringBootApplication
public class DecouverteSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DecouverteSpringBootApplication.class, args);
    }

}


/**
 * Principe de L'ioc expliquant la présence de ce fameux componentScan.
 * <p>
 * L'ioc ou Inversion of controls :
 * <p>
 * Flashback : L'opérateur d'instanciation : le new.
 * <p>
 * Lorsque je développe en orienté objet en java par exemple, lorsque je veux créer un objet, je dois utiliser
 * l'opérateur d'instanciation new.
 * <p>
 * En fait ,l'ioc, c'est le principe de retirer cette mission au développeur.
 * EN GROS, quand j'applique le design pattern de l'ioc, je ne fais plus de new. Je n'instancie plus moi-même les
 * objets.
 * <p>
 * C'est le système qui est chargé de le faire.
 * <p>
 * Comment est-ce que j'indique à Spring ce qu'il doit instancier dans son contexte. (les objets dont spring à besoin).
 * <p>
 * 1. via une interface
 * //public class Person implements toInstanciate {
 * //    private Long id;
 * //}
 * //
 * //
 * //
 * //=> ah ! trop bien une interface m'indiquant que je dois instancier l'objet
 * //
 * //    => new Person()
 * <p>
 * 2. via une annotation :
 * Même principe sauf qu'au lieu de vérifier s'il y a une interface d'implémenter sur une classe, on regarde
 * si une annotation y est attachée.
 *
 * @ToInstanciate //public class Person {
 * * //    private Long id;
 * * //}
 * * //
 * * //
 * * //
 * * //=> ah ! trop bien une annotation m'indiquant que je dois instancier l'objet
 * * //
 * * //    => new Person()
 */

/** Exemple d'annotation permettant d'instancier des éléments dans le contexte de spring :
 *
 * @Entity
 * @Service
 * @Repository
 * @RestController
 * @Configuration
 *
 */
