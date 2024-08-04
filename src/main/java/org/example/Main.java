package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {

        // Adding new product
        Product product = new Product();
        product.setName("pickle");
        product.setPrice(99);
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        }

        //Reading
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product retrivedProduct = session.get(Product.class, product.getId());
            System.out.println("Product: " + retrivedProduct.getName()
                    + ", price: " + retrivedProduct.getPrice());
        }

        //Updating
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            product.setName("new pickle");
            session.merge(product);
            transaction.commit();
        }

        //Deleting
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
        }

        HibernateUtil.shutdown();

    }
}