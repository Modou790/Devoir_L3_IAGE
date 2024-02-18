import Metier.fonctionalite;
import model.BD;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println(
                "Personne id nom prenom adresse(c'est [Dakar, Thies, Louga] Ennumeration)" +
                "Employe salaire zone(c'est PA , Mbao)" +
                "Directeur  Matricule(c'est premiere caractere du prenom +0000+ taille du nom) enume" +
                "pour IPersonne : getAllEmploye , getAllDirecteur, addPersonne, DeleteByMatricule ,UpdatePersonne" );

        int choix;

        fonctionalite fonctionalite = new fonctionalite();

        do {
            choix = fonctionalite.menu();

            switch (choix) {
                case 1:
                    fonctionalite.afficherToutesLesPersonnes();
                    break;
                case 2:
                    fonctionalite.afficherTousLesDirecteurs();
                    break;
                case 3:
                    fonctionalite.ajouterPersonne();
                    break;
                case 4:
                    fonctionalite.supprimerPersonneParMatricule();
                    break;
                case 5:
                    fonctionalite.mettreAJourPersonne();
                    break;
                default:
                    System.out.println("Ciao Ciao !!!");
            }
        } while (choix != 6);
    }
}