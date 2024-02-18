package model;
import java.sql.SQLException;
import java.util.List;
public interface IPersonne<T>{


    List<T> getAllPersonne() throws SQLException;
    List<T> getAllDirecteur() throws SQLException;
    void addPersonne(T t) throws SQLException;
    void deleteByMatricule(String matricule) throws SQLException;//T,t
    void updatePersonne(int id,T t) throws SQLException;
}


