package org.example;


public class Main {
    public static void main(String[] args) {
        ProductDAO.getAll();
        ProductDAO.update((long)1, "green apple");
        ProductDAO.getAll();
        ProductDAO.shutdown();
    }
}

