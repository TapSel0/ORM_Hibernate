package org.example;


public class Main {
    public static void main(String[] args) {
        ProductDAO.deleteById((long)8);
        ProductDAO.getAll();
        ProductDAO.shutdown();
    }
}

