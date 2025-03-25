// package data.dao;
// import pet.Pet;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

// public class PetDAO implements DAO<Pet> {
//     private Connection connection;

//     public PetDAO(Connection connection) {
//         this.connection = connection;
//     }

//     // @Override
//     // public void insert(Pet pet) throws SQLException {
//     //     String sql = "INSERT INTO pets (name, species, user_id) VALUES (?, ?, ?)";
//     //     try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//     //         stmt.setString(1, pet.getName());
//     //         stmt.setString(2, pet.getSpecies());
//     //         stmt.setInt(3, pet.getUserId());
//     //         stmt.executeUpdate();
//     //     }
//     // }

//     // @Override
//     // public Pet get(int id) throws SQLException {
//     //     String sql = "SELECT * FROM pets WHERE id = ?";
//     //     try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//     //         stmt.setInt(1, id);
//     //         ResultSet rs = stmt.executeQuery();
//     //         if (rs.next()) {
//     //             return new Pet(rs.getInt("id"), rs.getString("name"), rs.getString("species"), rs.getInt("user_id"));
//     //         }
//     //     }
//     //     return null;
//     // }

//     // @Override
//     // public List<Pet> getAll() throws SQLException {
//     //     List<Pet> pets = new ArrayList<>();
//     //     String sql = "SELECT * FROM pets";
//     //     try (Statement stmt = connection.createStatement();
//     //          ResultSet rs = stmt.executeQuery(sql)) {
//     //         while (rs.next()) {
//     //             pets.add(new Pet(rs.getInt("id"), rs.getString("name"), rs.getString("species"), rs.getInt("user_id")));
//     //         }
//     //     }
//     //     return pets;
//     // }

//     // @Override
//     // public void update(Pet pet) throws SQLException {
//     //     String sql = "UPDATE pets SET name = ?, species = ? WHERE id = ?";
//     //     try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//     //         stmt.setString(1, pet.getName());
//     //         stmt.setString(2, pet.getSpecies());
//     //         stmt.setInt(3, pet.getId());
//     //         stmt.executeUpdate();
//     //     }
//     // }

//     // @Override
//     // public void delete(int id) throws SQLException {
//     //     String sql = "DELETE FROM pets WHERE id = ?";
//     //     try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//     //         stmt.setInt(1, id);
//     //         stmt.executeUpdate();
//     //     }
//     // }

//     @Override
//     public List<Pet> getByUserId(int userId) throws SQLException {
//         List<Pet> pets = new ArrayList<>();
//         String sql = "SELECT * FROM pets WHERE user_id = ?";
//         try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//             stmt.setInt(1, userId);
//             ResultSet rs = stmt.executeQuery();
//             while (rs.next()) {
//                 pets.add(new Pet(rs.getInt("petId"), rs.getString("healthStatus")));
//             }
//         }
//         return pets;
//     }

// //     @Override
// //     public void insertForUser(int userId, Pet pet) throws SQLException {
// //         String sql = "INSERT INTO pets (name, species, user_id) VALUES (?, ?, ?)";
// //         try (PreparedStatement stmt = connection.prepareStatement(sql)) {
// //             stmt.setString(1, pet.getName());
// //             stmt.setString(2, pet.getSpecies());
// //             stmt.setInt(3, userId);
// //             stmt.executeUpdate();
// //         }
// //     }
// }
