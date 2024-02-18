package model;


public class Directeur extends Personne {
    private String Matricule;

    public Directeur(int id, String nom, String prenom, String adresse, String matricule) {
        super(id, nom, prenom, adresse);
        Matricule = matricule;
    }

    public Directeur() {

    }

    public String getMatricule() {
        return Matricule;
    }
    public void setMatricule(String matricule) {
        this.Matricule = matricule;
    }

    public String generateMatricule(String nom, String prenom) {
        if (prenom != null && !prenom.isEmpty()) {
            return prenom.substring(0, 1) + "0000" + nom.length();
        } else {
            // Gérer le cas où le prénom est null ou vide
            return "Matricule non généré";
        }
    }

    // public String generateMatricule(String nom, String prenom) {return getPrenom().substring(0, 1) + "0000" + getNom().length();}

    @Override
    public String toString() {
        return "Directeur{" +
                " nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ",adresse='" + adresse + '\'' +
                ",Matricule='" + generateMatricule(getNom(),getPrenom()) + '\'' +
                '}';
    }
}
