package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.persistence.*;
import java.util.List;

public class ProductDAO {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
    private static EntityManager em;
    private static EntityTransaction tx;
    private static SessionFactory sessionFactory;


    private static void createTransaction(){
        try{
            em = emf.createEntityManager();
            tx = em.getTransaction();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void createSession(){
        if (sessionFactory == null){
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(org.example.Product.class)
                    .buildSessionFactory();
        }
    }

    public static void shutdown(){
        if (emf != null) {
            emf.close();
        }
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }


    // Adding new product
    public static void save(String name, Double price){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        try {
            createTransaction();
            tx.begin();
            em.persist(product);
            tx.commit();
            System.out.println("Row was added");
        }
        catch (Exception e){
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
            throw e;
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //Reading
    public static void getById(Long id){
        try{
           createTransaction();
           tx.begin();
           Product product = em.find(Product.class, id);
           System.out.println("Key: " + product.getId() + ", name = " + product.getName() + ", price = " + product.getPrice());
           tx.commit();
        }
        catch (Exception e){
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
            throw e;
        }
        finally {
            if (em != null){
                em.close();
            }
        }
    }



    //Updating
    public static void update(Long id, String new_name){
        createSession();
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();

            Product product = session.get(Product.class, id);
            product.setName(new_name);

            session.update(product);
            transaction.commit();
            System.out.println("Row was changed");
        }
    }
    public static void update(Long id, Double new_price){
        createSession();
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();

            Product product = session.get(Product.class, id);
            product.setPrice(new_price);

            session.update(product);
            transaction.commit();
            System.out.println("Row was changed");
        }
    }
    public static void update(Long id, String new_name, Double new_price){
        createSession();
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();

            Product product = session.get(Product.class, id);
            product.setPrice(new_price);
            product.setName(new_name);

            session.update(product);
            transaction.commit();
            System.out.println("Row was changed");
        }
    }



    //Deleting
    public static void delete(Product product){
        createSession();
        try(Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
            System.out.println("Row deleted");
        }
    }
    public static void delete(Long id, String name, Double price){
        createSession();
        try(Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            Product product = new Product(id, name, price);
            session.delete(product);
            transaction.commit();
            System.out.println("Row deleted");
        }
    }


    //Select all
    public static void getAll(){
        try {
            createTransaction();
            tx.begin();
            // Выполнение запроса и получение результатов
            List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();

            // Вывод результатов
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice());
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
}
