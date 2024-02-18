package model;
enum zone {
    PA, MBAO
}
public class Employe extends Personne{
    private Integer salaire;
    private String zone;

    public Employe(int id, String nom, String prenom, String adresse, Integer salaire, String zone) {
        super(id, nom, prenom, adresse);
        this.salaire = salaire;
        this.zone = zone;
    }

    public Employe() {

    }

    public Integer getSalaire() {
        return salaire;
    }

    public void setSalaire(Integer salaire) {
        this.salaire = salaire;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "Employe{" +
                " nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ",salaire=" + salaire +
                ", zone='" + zone + '\'' +
                '}';
    }
}
