package org.example.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManages {
    private static final String Persistence_Unit_Name = "hostelmanagement";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(Persistence_Unit_Name);
    private EntityManager entityManager = factory.createEntityManager();

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
