package model;


import java.sql.Connection;
import java.sql.DriverManager;

public class BD {
    private final String server = "localhost";
    private final String username = "root";
    private final String password = "root";//macbook c'est root
    private final String bd = "devoir_iage";
    private final String url = "jdbc:mysql://" + server + ":8889/" + bd;
    private Connection conn;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connecté");
        } catch (Exception ex) {
            conn = null;
            System.out.print("Erreur");
            ex.printStackTrace(); // Afficher les détails de l'erreur
        }
        return conn;
    }
}
