package data.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    // void insert(T t);              // Insert entity
    // T get(int id);                 // Retrieve entity by ID
    // List<T> getAll();              // Retrieve all entities
    // void update(T t);              // Update entity
    // void delete(int id);           // Delete entity by ID
    
    // Retrieve entities by user ID
    List<T> getByUserId(int userId) throws SQLException;
    
    // Insert an entity under a given user ID
    //void insertForUser(int userId, T t);
}