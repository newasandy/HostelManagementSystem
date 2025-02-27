package org.example.testConnection;

import jakarta.persistence.*;

public class TestConnection {

    public void testConnection() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("hostelmanagement");
            System.out.println("Connection successful!");
            emf.close(); // Close the EntityManagerFactory when done
        } catch (jakarta.persistence.PersistenceException e) {
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
