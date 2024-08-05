package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TransactionEntity {
    // Adding new product
    public static void createEntity(String name, Double price){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
            HibernateUtil.shutdown();
        }
    }


    //Reading
    public static void readEntity(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product retrivedProduct = session.get(Product.class, id);
            System.out.println("Product: " + retrivedProduct.getName()
                    + ", price: " + retrivedProduct.getPrice());
            HibernateUtil.shutdown();
        }
    }



    //Updating
    public static void updateEntity(Long id, String new_name){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Product product = new Product();
            product.setName(new_name);
            product.setId(id);
            session.merge(product);
            transaction.commit();
            HibernateUtil.shutdown();
        }
    }
    public static void updateEntity(Long id, Double new_price){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Product product = new Product();
            product.setPrice(new_price);
            product.setId(id);
            session.merge(product);
            transaction.commit();
            HibernateUtil.shutdown();
        }
    }



    //Deleting
    public static void deleteEntity(Product product){
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
            HibernateUtil.shutdown();
        }
    }
    public static void deleteEntity(Long id, String name, Double price){
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Transaction transaction = session.beginTransaction();
            Product product = new Product(id, name, price);
            session.remove(product);
            transaction.commit();
            HibernateUtil.shutdown();
        }
    }


    //Select all
    public static void selectAll(){
        EntityManager em = DatabaseUtil.getEntityManager();
        try {
            // Выполнение запроса и получение результатов
            List<Product> products = em.createQuery("select p from Product p").getResultList();

            // Вывод результатов
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


}
