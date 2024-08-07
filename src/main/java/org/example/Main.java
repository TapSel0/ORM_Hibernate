package org.example;


public class Main {
    public static void main(String[] args) {
        HibernateUtil.selectAll();
        HibernateUtil.updateEntity((long)1, "green apple");
        HibernateUtil.selectAll();
        HibernateUtil.shutdown();
    }
}

