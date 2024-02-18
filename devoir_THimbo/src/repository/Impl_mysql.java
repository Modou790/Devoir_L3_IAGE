package repository;

import model.*;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Impl_mysql implements IPersonne<Personne> {
    private final Connection connection;

    public Impl_mysql()
    {
        this.connection = new BD().getConnection();
    }

    @Override
    public List<Personne> getAllPersonne() throws SQLException {
        List<Personne> personnes = new ArrayList<>();
        String sql = "SELECT * FROM personne";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result= statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adresse = result.getString("adresse");

                if (result.getString("salaire") != null) {
                    int salaire = result.getInt("salaire");
                    String zone = result.getString("zone");
                    Employe employe = new Employe(id, nom, prenom, adresse, salaire, zone);
                    personnes.add(employe);
                } else {
                    String matricule = result.getString("matricule");
                    Directeur directeur = new Directeur(id, nom, prenom, adresse, matricule);
                    personnes.add(directeur);
                }
            }

        return personnes;
    }

    @Override
    public List<Personne> getAllDirecteur() throws SQLException {
        List<Personne> directeurs = new ArrayList<>();
        String sql = "SELECT * FROM personne WHERE matricule IS NOT NULL";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result= statement.executeQuery();
        while (result.next()) {
            int id = result.getInt("id");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String adresse = result.getString("adresse");
            String matricule = result.getString("matricule");
            Directeur directeur = new Directeur(id, nom, prenom, adresse, matricule);
            directeurs.add(directeur);
        }
        return directeurs;
    }

    @Override
    public void addPersonne(Personne personne) throws SQLException {
       Boolean result= personne instanceof Employe;
       if(result) {
           String sql = "INSERT INTO Personne (nom, prenom, adresse, salaire, zone) VALUES (?, ?, ?, ?, ?)";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, personne.getNom());
           statement.setString(2, personne.getPrenom());
           statement.setString(3, personne.getAdresse());
           statement.setInt(4, ((Employe) personne).getSalaire());
           statement.setString(5, ((Employe) personne).getZone());
           statement.executeUpdate();
       } else {
           String sql = "INSERT INTO Personne (nom, prenom, adresse, matricule) VALUES (?, ?, ?, ?)";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, personne.getNom());
           statement.setString(2, personne.getPrenom());
           statement.setString(3, personne.getAdresse());
           statement.setString(4, ((Directeur) personne).getMatricule());
           statement.executeUpdate();
       }
    }

/// ici c'est la premiere version
    //@Override
    //public void addPersonne(Personne personne) throws SQLException {
    //        Boolean result= personne instanceof Employe;
    //        String choix = (result) ? "salaire, zone" : "matricule";
    //        String sql = "INSERT INTO Personne (nom, prenom, adresse, "+ choix +") VALUES (?, ?, ?, ?,?)";
    //        PreparedStatement statement = connection.prepareStatement(sql);
    //        statement.setString(1, personne.getNom());
    //        statement.setString(2, personne.getPrenom());
    //        statement.setString(3, personne.getAdresse());
    //
    //        if ((result)) {
    //            statement.setInt( 4, ((Employe) personne).getSalaire());
    //            statement.setString(5, ((Employe) personne).getZone());//marchre
    //        } else {
    //            statement.setString(4, ((Directeur) personne).getMatricule());
    //            statement.setString(5, "");//ne Marche pas
    //
    //        }
    //
    //        statement.executeUpdate();
    //    }

    @Override
    public void deleteByMatricule(String matricule) throws SQLException {
        String sql = "Delete from personne where matricule = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, matricule);
        statement.executeUpdate();
    }


    @Override
    public void updatePersonne(int id, Personne personne) throws SQLException {
        boolean result = personne instanceof Employe;
        String sql;
        if (result) {
            sql = "UPDATE Personne SET nom = ?, prenom = ?, adresse = ?, salaire = ?, zone = ? WHERE id = ?";
        } else {
            sql = "UPDATE Personne SET nom = ?, prenom = ?, adresse = ?, matricule = ? WHERE id = ?";
        }

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, personne.getNom());
        statement.setString(2, personne.getPrenom());
        statement.setString(3, personne.getAdresse());
        if (result) {
            statement.setInt(4, ((Employe) personne).getSalaire());
            statement.setString(5, ((Employe) personne).getZone());
        } else {
            statement.setString(4, ((Directeur) personne).getMatricule());
        }
        statement.setInt(6, id);
        statement.executeUpdate();
    }
}
