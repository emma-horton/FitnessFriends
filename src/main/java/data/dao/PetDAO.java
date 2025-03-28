package data.dao;

import pet.PetBehaviour;
import pet.PetFactory;
import pet.PetHealthStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetDAO {
    private Connection connection;

    public PetDAO(Connection connection) {
        this.connection = connection;
    }

    public PetBehaviour getPetForUser(int userId) throws SQLException {
        String sql = "SELECT * FROM VirtualPets WHERE userId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int petId = rs.getInt("petId");
                String petType = rs.getString("petType");
                String healthStatus = rs.getString("healthStatus");

                // Convert healthStatus to PetHealthStatus enum
                PetHealthStatus petHealthStatus = PetHealthStatus.valueOf(healthStatus.toUpperCase());

                // Use PetFactory to create the PetBehaviour object
                return PetFactory.createPet(petType, petId, petHealthStatus);
            }
        }
        throw new SQLException("No pet found for user with ID: " + userId);
    }
}