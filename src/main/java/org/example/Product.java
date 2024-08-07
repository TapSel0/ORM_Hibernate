package org.example;

import javax.persistence.*;


@Entity
@Table(name = "products")
class Product{
    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "price")
    public Double price;


    public Product(){}

    public Product(Long id, String name, Double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getPrice() {
        return price;
    }


}



