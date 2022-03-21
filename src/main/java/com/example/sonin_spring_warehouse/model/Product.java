package com.example.sonin_spring_warehouse.model;

import javax.persistence.*;

@Table(name = "products")
@Entity
public class Product {

    @Id
    @Column(name = "product_id")
    @SequenceGenerator(name = "spi", sequenceName="sequence_product_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spi")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        if (price >= 0) this.price = price;
        else this.price = 0;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity >= 0) this.quantity = quantity;
        else this.quantity = 0;
    }
}
