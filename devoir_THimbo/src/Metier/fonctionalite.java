package Metier;

import model.Directeur;
import model.Employe;
import model.Personne;

import model.adresse;
import repository.Impl_mysql;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class fonctionalite {
    Scanner clavier = new Scanner(System.in);

    private final Impl_mysql impl_mysql;
    public fonctionalite() {
        impl_mysql = new Impl_mysql();
    }


    public int menu() {
        int choix;

        System.out.println("1-Afficher toutes les personnes");
        System.out.println("2-Afficher tous les directeurs");
        System.out.println("3-Ajouter une personne");
        System.out.println("4-Supprimer une personne par matricule");
        System.out.println("5-Mettre à jour une personne");
        System.out.println("6-Quitter");
        do {
            choix = clavier.nextInt();
            if (choix < 1 || choix > 6) {
                System.out.println("Choix incorrect.");
            }
        } while (choix < 1 || choix > 6);
        return choix;
    }

    public int menuTypeCompte(){
        int choix;

        System.out.println("1-Employe");
        System.out.println("2-Directeur");
        do{
            choix = clavier.nextInt();
            if(choix<1 || choix >2){
                System.out.println("Le choix est incorrect .");
            }
        }while(choix<1 || choix >2);
        return  choix;
    }

    public void afficherToutesLesPersonnes() throws SQLException {
        List<Personne> personnes = impl_mysql.getAllPersonne();
        for (Personne p : personnes) {
            System.out.println(p.toString());
        }
    }

    public void afficherTousLesDirecteurs() throws SQLException {
        List<Personne> directeurs = impl_mysql.getAllDirecteur();
        for (Personne d : directeurs) {
            System.out.println(d.toString());
        }
    }

    public void ajouterPersonne() throws SQLException {
        int type = menuTypeCompte();
        Personne personne = (type == 1) ? new Employe() : new Directeur();
        clavier.nextLine();

        System.out.println("Saisir le nom de la personne : ");
        String nom = clavier.nextLine();
        personne.setNom(nom);

        System.out.println("Saisir le prénom de la personne : ");
        String prenom = clavier.nextLine();
        personne.setPrenom(prenom);

        System.out.println("Saisir l'adresse de la personne (Dakar, Thies, Lougga) : ");
        String adresseStr = clavier.nextLine();
        adresse adresseEnum = adresse.valueOf(adresseStr);
        personne.setAdresse(adresseEnum.toString());

        if (type == 1) {
            System.out.println("Saisir le salaire de la personne : ");
            int salaire = clavier.nextInt();
            clavier.nextLine();

            System.out.println("Saisir la zone de la personne : ");
            String zone = clavier.nextLine();

            ((Employe) personne).setSalaire(salaire);
            ((Employe) personne).setZone(zone);
        } else {
            String matricule = ((Directeur) personne).generateMatricule(nom, prenom);
            ((Directeur) personne).setMatricule(matricule);
            System.out.println("Le matricule de la personne est ajouter: \n");
        }

        impl_mysql.addPersonne(personne);
    }


    public void supprimerPersonneParMatricule() throws SQLException {
        clavier.nextLine();
        System.out.println("Saisir le matricule de la personne à supprimer : ");
        String matricule = clavier.nextLine();
        impl_mysql.deleteByMatricule(matricule);
    }

    public void mettreAJourPersonne() throws SQLException {
        int type = menuTypeCompte();
        Personne personne = (type == 1) ? new Employe() : new Directeur();
        clavier.nextLine(); // Nettoyer le tampon
        System.out.println("Saisir l'ID de la personne à mettre à jour : ");
        personne.setId(clavier.nextInt());
        clavier.nextLine(); // Nettoyer le tampon
        System.out.println("Saisir le nouveau nom : ");
        personne.setNom(clavier.nextLine());
        System.out.println("Saisir le nouveau prénom : ");
        personne.setPrenom(clavier.nextLine());
        System.out.println("Saisir la nouvelle adresse de la personne (Dakar, Thies, Lougga) : ");
        String adresseStr = clavier.nextLine();
        adresse adresseEnum = adresse.valueOf(adresseStr);
        personne.setAdresse(adresseEnum.toString());

        if (type == 1) {
            System.out.println("Saisir le nouveau salaire de la personne : ");
            ((Employe) personne).setSalaire(clavier.nextInt());
            clavier.nextLine();
            System.out.println("Saisir la nouvelle zone de la personne : ");
            ((Employe) personne).setZone(clavier.nextLine());
        } else {
            System.out.println("Saisir le nouveau matricule de la personne : ");
            ((Directeur) personne).setMatricule(clavier.nextLine());
        }
        impl_mysql.updatePersonne(personne.getId(), personne);
    }

}
