package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DatabaseUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close(){
        emf.close();
    }

    public static EntityTransaction getEntityTransaction(){
        return getEntityManager().getTransaction();
    }
}
